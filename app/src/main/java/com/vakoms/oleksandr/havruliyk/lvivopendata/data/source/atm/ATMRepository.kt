package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.RemoteATMDataStorage
import javax.inject.Inject

class ATMRepository @Inject constructor(
    localDataStorage: LocalATMDataStorage,
    remoteDataStorage: RemoteATMDataStorage,
    netManager: NetManager
) //: Repository<ATMRecord>(localDataStorage, remoteDataStorage, netManager)