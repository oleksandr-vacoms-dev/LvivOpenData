package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.pharmacy

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage


class PharmacyRepository(
    private val localDataStorage: DataStorage<PharmacyRecord>,
    private val remoteDataStorage: DataStorage<PharmacyRecord>,
    private val netManager: NetManager
) : DataStorage<PharmacyRecord> {

    companion object {
        const val TAG = "PharmacyRepository"
    }

    override fun getAllData(): LiveData<List<PharmacyRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getAllData()
                Log.i(TAG, "load pharmacy data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load pharmacy data from LocalDataStorage")
                localDataStorage.getAllData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<PharmacyRecord>?) {
        deleteAllData()
        data?.let { saveData(it) }
    }

    override fun saveData(data: List<PharmacyRecord>) {
        localDataStorage.saveData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}