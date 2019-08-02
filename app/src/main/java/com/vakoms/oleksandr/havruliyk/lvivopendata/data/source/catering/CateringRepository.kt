package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class CateringRepository(
    private val localDataStorage: DataStorage<CateringRecord>,
    private val remoteDataStorage: DataStorage<CateringRecord>,
    private val netManager: NetManager
) : DataStorage<CateringRecord> {

    companion object {
        const val TAG = "CateringRepository"
    }

    override fun getAllData(): LiveData<List<CateringRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getAllData()
                Log.i(TAG, "load catering data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load catering data from LocalDataStorage")
                localDataStorage.getAllData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<CateringRecord>?) {
        deleteAllData()
        data?.let { saveData(it) }
    }

    override fun saveData(data: List<CateringRecord>) {
        localDataStorage.saveData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}