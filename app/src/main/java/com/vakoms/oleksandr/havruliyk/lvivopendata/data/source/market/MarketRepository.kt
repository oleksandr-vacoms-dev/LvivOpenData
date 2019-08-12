package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.MarketBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.MarketByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

class MarketRepository @Inject constructor(
    var localDataStorage: LocalMarketDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : DataStorage<MarketRecord> {

    @MainThread
    override fun getData(): Listing<MarketRecord> {
        val boundaryCallback = MarketBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList = localDataStorage.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = boundaryCallback
        )

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getDataByName(name: String): Listing<MarketRecord> {
        val boundaryCallback = MarketByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor,
            name = name
        )
        val livePagedList = localDataStorage.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = boundaryCallback
        )

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAllData(newData: List<MarketRecord>) {
        localDataStorage.saveAll(newData)
    }

    private fun getListing(
        boundaryCallback: DataBoundaryCallback<MarketRecord>,
        livePagedList: LiveData<PagedList<MarketRecord>>,
        refreshStateLiveData: () -> LiveData<NetworkState>
    ): Listing<MarketRecord> {

        // we are using a mutable live data to trigger refresh requests which eventually calls
        // refresh method and gets a new live data. Each refresh request by the user becomes a newly
        // dispatched data in refreshTrigger
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refreshStateLiveData()
        }

        return Listing(
            pagedList = livePagedList,
            networkState = boundaryCallback.networkState,
            retry = {
                boundaryCallback.helper.retryAllFailed()
            },
            refresh = {
                refreshTrigger.value = null
            },
            refreshState = refreshState
        )
    }


    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = networkState.value
        openDataApi.getMarkets(sqlMarkets(FIRST_ITEM)).enqueue(
            object : Callback<MarketsResponse> {
                override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<MarketsResponse>,
                    response: Response<MarketsResponse>
                ) {
                    ioExecutor.execute {
                        saveAllData(response.body().result.records)
                        networkState.postValue(NetworkState.LOADED)
                    }
                }
            }
        )
        return networkState
    }

    @MainThread
    private fun refresh(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = networkState.value
        openDataApi.getMarkets(sqlMarketsSearchByName(name, FIRST_ITEM)).enqueue(
            object : Callback<MarketsResponse> {
                override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<MarketsResponse>,
                    response: Response<MarketsResponse>
                ) {
                    ioExecutor.execute {
                        saveAllData(response.body().result.records)
                        networkState.postValue(NetworkState.LOADED)
                    }
                }
            }
        )
        return networkState
    }
}