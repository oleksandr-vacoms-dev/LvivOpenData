package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FitnessRepository @Inject constructor(
    var localDataStorage: LocalFitnessDataStorage,
    var remoteDataStorage: RemoteFitnessDataStorage,
    var netManager: NetManager
) : FitnessDataStorage {

    companion object {
        const val TAG = "FitnessRepository"
    }

    override fun getFitnessData(): LiveData<List<FitnessCentersRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getFitnessData()
                Log.i(TAG, "load fitness data from RemoteDataStorage")

                data.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load fitness data from LocalDataStorage")
                localDataStorage.getFitnessData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<FitnessCentersRecord>?) {
        deleteAllData()
        data?.let { saveFitnessData(it) }
    }

    override fun saveFitnessData(data: List<FitnessCentersRecord>) {
        localDataStorage.saveFitnessData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}