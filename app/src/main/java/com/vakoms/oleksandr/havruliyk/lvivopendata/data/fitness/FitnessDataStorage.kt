package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse

interface FitnessDataStorage {

    fun getFitnessData(): MutableLiveData<List<FitnessCentersRecord>>

    fun saveFitnessData(data: List<FitnessCentersRecord>)

    fun destroyInstance()
}