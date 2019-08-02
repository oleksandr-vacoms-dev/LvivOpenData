package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market

import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord


class MarketRepository(
    private val localDataStorage: LocalMarketDataStorage,
    private val remoteDataStorage: RemoteMarketDataStorage,
    private val netManager: NetManager
) : MarketDataStorage {

    companion object {
        const val TAG = "MarketRepository"
    }

    override fun getMarketData(): LiveData<List<MarketRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage.getMarketData()
                Log.i(TAG, "load market data from RemoteDataStorage")

                data.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load market data from LocalDataStorage")
                localDataStorage.getMarketData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<MarketRecord>?) {
        deleteAllData()
        data?.let { saveMarketData(it) }
    }

    override fun saveMarketData(data: List<MarketRecord>) {
        localDataStorage.saveMarketData(data)
    }

    override fun deleteAllData() {
        localDataStorage.deleteAllData()
    }
}