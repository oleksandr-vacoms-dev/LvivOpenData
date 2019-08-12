package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.CateringBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.CateringByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.CateringRefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlCatering
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlCateringSearchByName
import java.util.concurrent.Executor
import javax.inject.Inject

class CateringRepository @Inject constructor(
    var localDataStorage: LocalCateringDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : Repository<CateringRecord>() {

    @MainThread
    override fun getAll(): Listing<CateringRecord> {
        val boundaryCallback = CateringBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

        return getListing(boundaryCallback, livePagedList) { refresh() }
    }

    @MainThread
    override fun getByName(name: String): Listing<CateringRecord> {
        val boundaryCallback = CateringByNameBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAll(data) },
            ioExecutor = ioExecutor,
            name = name
        )

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refreshByName(name) }
    }

    override fun saveAll(newData: List<CateringRecord>) {
        localDataStorage.saveAll(newData)
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getCatering(sqlCatering(FIRST_ITEM)).enqueue(
            CateringRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }

    @MainThread
    private fun refreshByName(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getCatering(sqlCateringSearchByName(name, FIRST_ITEM)).enqueue(
            CateringRefreshCallback(networkState, ioExecutor) { response ->
                saveAll(response.body().result.records)
            })

        return networkState
    }
}