package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.callback.MarketBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.callback.MarketByNameBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.callback.MarketRefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlMarkets
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlMarketsSearchByName
import java.util.concurrent.Executor
import javax.inject.Inject

class MarketRepository @Inject constructor(
    var localDataStorage: LocalMarketDataStorage,
    var openDataApi: OpenDataApi,
    var ioExecutor: Executor
) : Repository<MarketRecord>() {

    @MainThread
    override fun getData(): Listing<MarketRecord> {
        val boundaryCallback = MarketBoundaryCallback(
            webservice = openDataApi,
            handleResponse = { data -> saveAllData(data) },
            ioExecutor = ioExecutor
        )

        val livePagedList =
            localDataStorage.getAll(boundaryCallback)

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

        val livePagedList =
            localDataStorage.getByName(boundaryCallback, name)

        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAllData(newData: List<MarketRecord>) {
        localDataStorage.saveAll(newData)
    }

    @MainThread
    private fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getMarkets(sqlMarkets(FIRST_ITEM)).enqueue(
            MarketRefreshCallback(networkState, ioExecutor) { response ->
                saveAllData(response.body().result.records)
            })

        return networkState
    }

    @MainThread
    private fun refresh(name: String): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()

        openDataApi.getMarkets(sqlMarketsSearchByName(name, FIRST_ITEM)).enqueue(
            MarketRefreshCallback(networkState, ioExecutor) { response ->
                saveAllData(response.body().result.records)
            })

        return networkState
    }
}