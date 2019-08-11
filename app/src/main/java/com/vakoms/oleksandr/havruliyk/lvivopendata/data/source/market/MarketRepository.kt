package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.MarketBoundaryCallback
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
) : Repository<MarketRecord>(localDataStorage, openDataApi) {

    @MainThread
    override fun getData(): Listing<MarketRecord> {
        val boundaryCallback = MarketBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> localDataStorage.saveAll(data) },
            ioExecutor = ioExecutor
        )

        // we are using a mutable live data to trigger refresh requests which eventually calls
        // refresh method and gets a new live data. Each refresh request by the user becomes a newly
        // dispatched data in refreshTrigger
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) {
            refresh()
        }

        val livePagedList = localDataStorage.marketDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = boundaryCallback
        )

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
        openDataApi.getMarkets(getSqlOrderedById(MARKET_ID, FIRST_ITEM, PAGE_SIZE)).enqueue(
            object : Callback<MarketsResponse> {
                override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<MarketsResponse>,
                    response: Response<MarketsResponse>
                ) {
                    ioExecutor.execute {

                        if (response.isSuccessful) {
                            localDataStorage.saveAll(response.body().result.records)
                        }
                        networkState.postValue(NetworkState.LOADED)
                    }
                }
            }
        )
        return networkState
    }
}