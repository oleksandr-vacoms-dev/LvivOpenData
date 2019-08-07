package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import org.jetbrains.anko.doAsync

abstract class Repository<T>(
    private val localDataStorage: DataStorage<T>,
    private val remoteDataStorage: DataStorage<T>,
    private val netManager: NetManager
) : DataStorage<T> {

    override fun getAll(): LiveData<List<T>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<T>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    override fun getById(id: Int): LiveData<T>? = localDataStorage.getById(id)


    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<T>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<T>?) {
        doAsync {
            deleteAll()
            data?.let { saveAll(it) }
        }
    }
}