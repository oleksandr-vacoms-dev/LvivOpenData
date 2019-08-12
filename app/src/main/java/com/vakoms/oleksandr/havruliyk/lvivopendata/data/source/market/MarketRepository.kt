package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.Status
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class MarketRepository @Inject constructor(
    private val localDataStorage: LocalMarketDataStorage,
    private val remoteDataSource: RemoteMarketDataStorage
) : Repository<MarketRecord>() {

    fun getAll_(): MutableLiveData<Listing<MarketRecord>> {
        val remoteListing = remoteDataSource.getAll()
        val localListing = localDataStorage.getAll()

        val listing = MutableLiveData<Listing<MarketRecord>>()

        listing.value = Listing(
            pagedList = remoteListing.pagedList,
            refresh = remoteListing.refresh,
            retry = remoteListing.retry,
            refreshState = remoteListing.refreshState,
            networkState = remoteListing.networkState
        )

        remoteListing.pagedList.observeForever {
            doAsync { localDataStorage.saveAll(it.toList()) }
        }

        remoteListing.networkState.observeForever {
            when (it.status) {
                Status.FAILED -> listing.value = Listing(
                    pagedList = localListing.pagedList,
                    refresh = remoteListing.refresh,
                    retry = remoteListing.retry,
                    refreshState = remoteListing.refreshState,
                    networkState = remoteListing.networkState
                )
                else -> listing.value = Listing(
                    pagedList = remoteListing.pagedList,
                    refresh = remoteListing.refresh,
                    retry = remoteListing.retry,
                    refreshState = remoteListing.refreshState,
                    networkState = remoteListing.networkState
                )
            }
        }

        return listing
    }

    override fun getAll(): Listing<MarketRecord> {
        val remoteListing = remoteDataSource.getAll()
        val localListing = localDataStorage.getAll()



        remoteListing.pagedList.observeForever {
            doAsync { localDataStorage.saveAll(it.toList()) }
        }

        return Listing(
            pagedList = remoteListing.pagedList,
            refresh = remoteListing.refresh,
            retry = remoteListing.retry,
            refreshState = remoteListing.refreshState,
            networkState = remoteListing.networkState
        )
    }

    @MainThread
    override fun getByName(name: String): Listing<MarketRecord> {
        return localDataStorage.getByName(name)
//        val boundaryCallback = MarketByNameBoundaryCallback(
//            webservice = openDataApi,
//            handleResponse = { data -> saveAll(data) },
//            ioExecutor = ioExecutor,
//            name = name
//        )
//
//        val livePagedList =
//            localDataStorage.getByName(boundaryCallback, name)
//
//        return getListing(boundaryCallback, livePagedList) { refresh(name) }
    }

    override fun saveAll(newData: List<MarketRecord>) {
        localDataStorage.saveAll(newData)
    }

//    @MainThread
//    private fun refresh(): LiveData<NetworkState> {
//        val networkState = MutableLiveData<NetworkState>()
//
//        openDataApi.getMarkets(sqlMarkets(FIRST_ITEM)).enqueue(
//            MarketRefreshCallback(
//                networkState,
//                ioExecutor
//            ) { response ->
//                saveAll(response.body().result.records)
//            })
//
//        return networkState
//    }
//
//    @MainThread
//    private fun refresh(name: String): LiveData<NetworkState> {
//        val networkState = MutableLiveData<NetworkState>()
//
//        openDataApi.getMarkets(sqlMarketsSearchByName(name, FIRST_ITEM)).enqueue(
//            MarketRefreshCallback(
//                networkState,
//                ioExecutor
//            ) { response ->
//                saveAll(response.body().result.records)
//            })
//
//        return networkState
//    }
}