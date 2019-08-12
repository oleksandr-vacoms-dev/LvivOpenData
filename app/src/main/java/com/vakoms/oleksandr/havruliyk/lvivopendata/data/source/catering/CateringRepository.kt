package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.CateringBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.CateringByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject


class CateringRepository @Inject constructor(
    var localDataStorage: LocalCateringDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : DataStorage<CateringRecord> {

    @MainThread
    override fun getData(): Listing<CateringRecord> {
        val boundaryCallback = CateringBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> localDataStorage.saveAll(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList = localDataStorage.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = boundaryCallback
        )

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getDataByName(name: String): Listing<CateringRecord> {
        val boundaryCallback = CateringByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> localDataStorage.saveAll(data) },
            ioExecutor = ioExecutor,
            name = name
        )
        val livePagedList = localDataStorage.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = boundaryCallback
        )

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    private fun getListing(
        boundaryCallback: DataBoundaryCallback<CateringRecord>,
        livePagedList: LiveData<PagedList<CateringRecord>>,
        refreshStateLiveData: () -> LiveData<NetworkState>
    ): Listing<CateringRecord> {

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
        openDataApi.getCatering(sqlCatering(FIRST_ITEM)).enqueue(
            object : Callback<CateringResponse> {
                override fun onFailure(call: Call<CateringResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<CateringResponse>,
                    response: Response<CateringResponse>
                ) {
                    ioExecutor.execute {
                        localDataStorage.saveAll(response.body().result.records)
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
        openDataApi.getCatering(sqlCateringSearchByName(name, FIRST_ITEM)).enqueue(
            object : Callback<CateringResponse> {
                override fun onFailure(call: Call<CateringResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<CateringResponse>,
                    response: Response<CateringResponse>
                ) {
                    ioExecutor.execute {
                        localDataStorage.saveAll(response.body().result.records)
                        networkState.postValue(NetworkState.LOADED)
                    }
                }
            }
        )
        return networkState
    }
}