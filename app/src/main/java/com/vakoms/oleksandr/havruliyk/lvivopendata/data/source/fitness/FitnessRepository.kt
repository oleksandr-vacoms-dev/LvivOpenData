package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.Repository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.RemoteFitnessDataStorage
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    localDataStorage: LocalFitnessDataStorage,
    remoteDataStorage: RemoteFitnessDataStorage,
    netManager: NetManager
) //: Repository<FitnessRecord>(localDataStorage, remoteDataStorage, netManager)