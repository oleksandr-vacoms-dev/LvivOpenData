package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class FitnessRepository(
    private val localDataStorage: DataStorage<FitnessRecord>,
    private val remoteDataStorage: DataStorage<FitnessRecord>,
    private val netManager: NetManager
) : DataStorage<FitnessRecord> {

    override fun getAll(): LiveData<List<FitnessRecord>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<FitnessRecord>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<FitnessRecord>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<FitnessRecord>?) {
        deleteAll()
        data?.let { saveAll(it) }
    }
}