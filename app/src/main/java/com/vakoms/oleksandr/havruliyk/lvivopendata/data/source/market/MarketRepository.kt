package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import javax.inject.Inject

class MarketRepository @Inject constructor(
    var localDataStorage: LocalMarketDataStorage,
    var remoteDataStorage: RemoteMarketDataStorage,
    netManager: NetManager
) : Repository<MarketRecord>(localDataStorage, remoteDataStorage, netManager) {


//    fun getAll_(): Listing<MarketRecord>? {
//        //return remoteDataStorage.getAll_()
//        val network = MutableLiveData<NetworkState>()
//        val initial = MutableLiveData<NetworkState>()
//        return Listing(localDataStorage.selectPaged(), network, initial)
//    }
}