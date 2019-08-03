package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository

class CateringRepository(
    localDataStorage: DataStorage<CateringRecord>,
    remoteDataStorage: DataStorage<CateringRecord>,
    netManager: NetManager
) : Repository<CateringRecord>(localDataStorage, remoteDataStorage, netManager)