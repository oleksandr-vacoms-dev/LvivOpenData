package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository

class FitnessRepository(
    localDataStorage: DataStorage<FitnessRecord>,
    remoteDataStorage: DataStorage<FitnessRecord>,
    netManager: NetManager
) : Repository<FitnessRecord>(localDataStorage, remoteDataStorage, netManager)