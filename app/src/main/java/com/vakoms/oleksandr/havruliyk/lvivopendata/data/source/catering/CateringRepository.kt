package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class CateringRepository(
    private val localDataStorage: DataStorage<CateringRecord>,
    private val remoteDataStorage: DataStorage<CateringRecord>,
    private val netManager: NetManager
) : DataStorage<CateringRecord> {

    override fun getAll(): LiveData<List<CateringRecord>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<CateringRecord>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<CateringRecord>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<CateringRecord>?) {
        deleteAll()
        data?.let { saveAll(it) }
    }
}