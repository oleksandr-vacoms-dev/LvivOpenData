package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class FitnessRepository(
    private val localDataStorage: DataStorage<FitnessCentersRecord>,
    private val remoteDataStorage: DataStorage<FitnessCentersRecord>,
    private val netManager: NetManager
) : DataStorage<FitnessCentersRecord> {

    companion object {
        const val TAG = "FitnessRepository"
    }

    override fun getAllData(): LiveData<List<FitnessCentersRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getAllData()
                Log.i(TAG, "load fitness data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load fitness data from LocalDataStorage")
                localDataStorage.getAllData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<FitnessCentersRecord>?) {
        deleteAllData()
        data?.let { saveData(it) }
    }

    override fun saveData(data: List<FitnessCentersRecord>) {
        localDataStorage.saveData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}