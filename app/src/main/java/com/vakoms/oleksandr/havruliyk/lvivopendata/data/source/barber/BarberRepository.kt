package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.LocalBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.RemoteBarberDataStorage
import javax.inject.Inject

class BarberRepository @Inject constructor(
    localDataStorage: LocalBarberDataStorage,
    remoteDataStorage: RemoteBarberDataStorage,
    netManager: NetManager
) : Repository<BarberRecord>(localDataStorage, remoteDataStorage, netManager)