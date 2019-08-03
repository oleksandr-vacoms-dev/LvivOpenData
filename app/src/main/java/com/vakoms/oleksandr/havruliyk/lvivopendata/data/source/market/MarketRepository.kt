package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class MarketRepository(
    private val localDataStorage: DataStorage<MarketRecord>,
    private val remoteDataStorage: DataStorage<MarketRecord>,
    private val netManager: NetManager
) : DataStorage<MarketRecord> {

    override fun getAll(): LiveData<List<MarketRecord>>? {
        netManager.isConnectedToInternet?.let {
            return if (it) {
                getDataFromRemoteAndRefreshLocal()
            } else {
                localDataStorage.getAll()
            }
        }
        return null
    }

    override fun saveAll(data: List<MarketRecord>) {
        localDataStorage.saveAll(data)
    }

    override fun deleteAll() {
        localDataStorage.deleteAll()
    }

    private fun getDataFromRemoteAndRefreshLocal(): LiveData<List<MarketRecord>>? {
        val data = remoteDataStorage.getAll()
        data?.observeForever { refreshSavedData(it) }
        return data
    }

    private fun refreshSavedData(data: List<MarketRecord>?) {
        deleteAll()
        data?.let { saveAll(it) }
    }
}