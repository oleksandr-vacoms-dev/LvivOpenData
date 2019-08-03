package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class BarberRepository(
    private val localDataStorage: DataStorage<BarberRecord>,
    private val remoteDataStorage: DataStorage<BarberRecord>,
    private val netManager: NetManager
) : DataStorage<BarberRecord> {

    override fun getAll(): LiveData<List<BarberRecord>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<BarberRecord>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<BarberRecord>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<BarberRecord>?) {
        deleteAll()
        data?.let { saveAll(it) }
    }
}