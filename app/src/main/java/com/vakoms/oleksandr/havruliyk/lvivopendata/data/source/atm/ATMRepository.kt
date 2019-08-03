package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage


class ATMRepository(
    private val localDataStorage: DataStorage<ATMRecord>,
    private val remoteDataStorage: DataStorage<ATMRecord>,
    private val netManager: NetManager
) : DataStorage<ATMRecord> {

    companion object {
        const val TAG = "ATMRepository"
    }

    override fun getAllData(): LiveData<List<ATMRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getAllData()
                Log.i(TAG, "load atm data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load atm data from LocalDataStorage")
                localDataStorage.getAllData()
            }
        }

        return null
    }

    override fun saveData(data: List<ATMRecord>) {
        localDataStorage.saveData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }

    private fun upDataSavedData(data: List<ATMRecord>?) {
        deleteAllData()
        data?.let { saveData(it) }
    }
}