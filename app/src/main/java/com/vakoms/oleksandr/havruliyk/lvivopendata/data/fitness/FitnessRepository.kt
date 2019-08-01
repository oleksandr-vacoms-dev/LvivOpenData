package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

class FitnessRepository(context: Context) : FitnessDataStorage {

    companion object {
        const val TAG = "FitnessRepository"

        private var INSTANCE: FitnessRepository? = null

        fun getInstance(context: Context): FitnessRepository? {
            if (INSTANCE == null) {
                INSTANCE = FitnessRepository(context)
            }
            return INSTANCE
        }
    }

    private var localDataStorage = LocalFitnessDataStorage.getInstance(context)
    private var remoteDataStorage = RemoteFitnessDataStorage.getInstance()

    override fun getFitnessData(): MutableLiveData<List<FitnessCentersRecord>> {
        val data = remoteDataStorage?.getFitnessData()
        return if (data?.value != null) {
            Log.i(TAG, "load fitness data from LocalDataStorage")
            saveFitnessData(data.value!!)
            data
        } else {
            Log.i(TAG, "load fitness data from LocalDataStorage")
            localDataStorage?.getFitnessData()!!
        }
    }

    override fun saveFitnessData(data: List<FitnessCentersRecord>) {
        localDataStorage?.saveFitnessData(data)
    }

    override fun destroyInstance() {
        INSTANCE = null
    }
}