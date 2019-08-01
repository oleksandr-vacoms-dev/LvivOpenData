package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
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
    private val netManager = NetManager(context)

    override fun getFitnessData(): LiveData<List<FitnessCentersRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage?.getFitnessData()
                Log.i(TAG, "load fitness data from RemoteDataStorage")
                data?.observeForever {
                    saveFitnessData(data.value!!)
                }
                return data
            } else {
                Log.i(TAG, "load fitness data from LocalDataStorage")
                localDataStorage?.getFitnessData()!!
            }
        }

        return null
    }

    override fun saveFitnessData(data: List<FitnessCentersRecord>) {
        localDataStorage?.saveFitnessData(data)
    }

    override fun destroyInstance() {
        INSTANCE = null
    }
}