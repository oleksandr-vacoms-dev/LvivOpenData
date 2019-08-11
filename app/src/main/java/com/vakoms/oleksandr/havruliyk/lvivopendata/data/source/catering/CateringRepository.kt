package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.RemoteCateringDataStorage
import javax.inject.Inject

class CateringRepository @Inject constructor(
    localDataStorage: LocalCateringDataStorage,
    remoteDataStorage: RemoteCateringDataStorage,
    netManager: NetManager
) //: Repository<CateringRecord>(localDataStorage, remoteDataStorage, netManager)