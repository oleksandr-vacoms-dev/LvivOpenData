package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class ATMRepository(
    private val localDataStorage: DataStorage<ATMRecord>,
    private val remoteDataStorage: DataStorage<ATMRecord>,
    private val netManager: NetManager
) : DataStorage<ATMRecord> {

    override fun getAll(): LiveData<List<ATMRecord>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<ATMRecord>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<ATMRecord>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<ATMRecord>?) {
        deleteAll()
        data?.let { saveAll(it) }
    }
}