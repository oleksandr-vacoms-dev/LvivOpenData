package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class BarberRepository(
    private val localDataStorage: DataStorage<BarberRecord>,
    private val remoteDataStorage: DataStorage<BarberRecord>,
    private val netManager: NetManager
) : DataStorage<BarberRecord> {

    companion object {
        const val TAG = "BarberRepository"
    }

    override fun getAllData(): LiveData<List<BarberRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getAllData()
                Log.i(TAG, "load barber data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load barber data from LocalDataStorage")
                localDataStorage.getAllData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<BarberRecord>?) {
        deleteAllData()
        data?.let { saveData(it) }
    }

    override fun saveData(data: List<BarberRecord>) {
        localDataStorage.saveData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}