package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.FitnessBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.FitnessByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlFitness
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlFitnessSearchByName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    var localDataStorage: LocalFitnessDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : DataStorage<FitnessRecord> {

    @MainThread
    override fun getData(): Listing<FitnessRecord> {
        val boundaryCallback = FitnessBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getDataByName(name: String): Listing<FitnessRecord> {
        val boundaryCallback = FitnessByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor,
            name = name
        )

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAllData(newData: List<FitnessRecord>) {
        localDataStorage.saveAll(newData)
    }

    private fun getListing(
        boundaryCallback: DataBoundaryCallback<FitnessRecord>,
        livePagedList: LiveData<PagedList<FitnessRecord>>,
        refreshStateLiveData: () -> LiveData<NetworkState>
    ): Listing<FitnessRecord> {

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
        openDataApi.getFitness(sqlFitness(FIRST_ITEM)).enqueue(
            object : Callback<FitnessResponse> {
                override fun onFailure(call: Call<FitnessResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<FitnessResponse>,
                    response: Response<FitnessResponse>
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
        openDataApi.getFitness(sqlFitnessSearchByName(name, FIRST_ITEM)).enqueue(
            object : Callback<FitnessResponse> {
                override fun onFailure(call: Call<FitnessResponse>, t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

                override fun onResponse(
                    call: Call<FitnessResponse>,
                    response: Response<FitnessResponse>
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