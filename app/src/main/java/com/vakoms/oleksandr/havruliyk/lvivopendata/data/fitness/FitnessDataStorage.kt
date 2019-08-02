package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

interface FitnessDataStorage {

    fun getFitnessData(): LiveData<List<FitnessCentersRecord>>?

    fun saveFitnessData(data: List<FitnessCentersRecord>)

    fun deleteAllData()
}