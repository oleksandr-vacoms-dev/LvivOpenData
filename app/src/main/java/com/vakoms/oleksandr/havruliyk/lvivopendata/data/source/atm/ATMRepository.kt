package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.ATMBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.ATMByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.ATMRefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlATM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlATMSearchByName
import java.util.concurrent.Executor
import javax.inject.Inject

class ATMRepository @Inject constructor(
    var localDataStorage: LocalATMDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : Repository<ATMRecord>() {

    @MainThread
    override fun getAll(): Listing<ATMRecord> {
        val boundaryCallback = ATMBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getByName(name: String): Listing<ATMRecord> {
        val boundaryCallback = ATMByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor,
            name = name
        )

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAll(newData: List<ATMRecord>) {
        localDataStorage.saveAll(newData)
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getATM(sqlATM(FIRST_ITEM)).enqueue(
            ATMRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }

    @MainThread
    private fun refresh(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getATM(sqlATMSearchByName(name, FIRST_ITEM)).enqueue(
            ATMRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }
}