package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.LocalBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.BarberBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.BarberByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.BarberRefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlBarber
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlBarberSearchByName
import java.util.concurrent.Executor
import javax.inject.Inject

class BarberRepository @Inject constructor(
    var localDataStorage: LocalBarberDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : Repository<BarberRecord>() {

    @MainThread
    override fun getData(): Listing<BarberRecord> {
        val boundaryCallback = BarberBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getDataByName(name: String): Listing<BarberRecord> {
        val boundaryCallback = BarberByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor,
            name = name
        )

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAllData(newData: List<BarberRecord>) {
        localDataStorage.saveAll(newData)
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getBarber(sqlBarber(FIRST_ITEM)).enqueue(
            BarberRefreshCallback(networkState, ioExecutor) { response ->
                saveAllData(response.body().result.records)
            })

        return networkState
    }

    @MainThread
    private fun refresh(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getBarber(sqlBarberSearchByName(name, FIRST_ITEM)).enqueue(
            BarberRefreshCallback(networkState, ioExecutor) { response ->
                saveAllData(response.body().result.records)
            })

        return networkState
    }
}