package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.FitnessBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.FitnessByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.FitnessRefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlFitness
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlFitnessSearchByName
import java.util.concurrent.Executor
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    var localDataStorage: LocalFitnessDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : Repository<FitnessRecord>() {

    @MainThread
    override fun getAll(): Listing<FitnessRecord> {
        val boundaryCallback = FitnessBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getByName(name: String): Listing<FitnessRecord> {
        val boundaryCallback = FitnessByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor,
            name = name
        )

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAll(newData: List<FitnessRecord>) {
        localDataStorage.saveAll(newData)
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getFitness(sqlFitness(FIRST_ITEM)).enqueue(
            FitnessRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }

    @MainThread
    private fun refresh(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getFitness(sqlFitnessSearchByName(name, FIRST_ITEM)).enqueue(
            FitnessRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }
}