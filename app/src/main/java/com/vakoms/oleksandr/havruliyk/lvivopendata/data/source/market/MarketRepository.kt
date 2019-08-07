package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import javax.inject.Inject

class MarketRepository @Inject constructor(
    localDataStorage: LocalMarketDataStorage,
    remoteDataStorage: RemoteMarketDataStorage,
    netManager: NetManager
) : Repository<MarketRecord>(localDataStorage, remoteDataStorage, netManager)