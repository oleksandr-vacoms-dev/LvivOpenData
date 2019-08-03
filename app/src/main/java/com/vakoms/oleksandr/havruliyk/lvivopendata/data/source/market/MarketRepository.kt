package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository

class MarketRepository(
    localDataStorage: DataStorage<MarketRecord>,
    remoteDataStorage: DataStorage<MarketRecord>,
    netManager: NetManager
) : Repository<MarketRecord>(localDataStorage, remoteDataStorage, netManager)