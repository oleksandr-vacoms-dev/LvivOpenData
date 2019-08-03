package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository

class ATMRepository(
    localDataStorage: DataStorage<ATMRecord>,
    remoteDataStorage: DataStorage<ATMRecord>,
    netManager: NetManager
) : Repository<ATMRecord>(localDataStorage, remoteDataStorage, netManager)