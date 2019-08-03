package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository

class BarberRepository(
    localDataStorage: DataStorage<BarberRecord>,
    remoteDataStorage: DataStorage<BarberRecord>,
    netManager: NetManager
) : Repository<BarberRecord>(localDataStorage, remoteDataStorage, netManager)