package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import java.util.concurrent.Executor
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    var localDataStorage: LocalFitnessDataStorage,
    var openDataApi: OpenDataApi
) {


}