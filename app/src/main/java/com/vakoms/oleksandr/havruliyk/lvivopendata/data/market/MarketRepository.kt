package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord

class MarketRepository(context: Context) : MarketDataStorage {

    companion object {
        const val TAG = "MarketRepository"

        private var INSTANCE: MarketRepository? = null

        fun getInstance(context: Context): MarketRepository? {
            if (INSTANCE == null) {
                INSTANCE = MarketRepository(context)
            }
            return INSTANCE
        }
    }

    private var localDataStorage = LocalMarketDataStorage.getInstance(context)
    private var remoteDataStorage = RemoteMarketDataStorage.getInstance()
    private val netManager = NetManager(context)

    override fun getMarketData(): LiveData<List<MarketsRecord>>? {

        netManager.isConnectedToInternet?.let {
            return if (it) {
                val data = remoteDataStorage?.getMarketData()
                Log.i(TAG, "load market data from RemoteDataStorage")

                data?.observeForever { upDataSavedData(data.value) }

                return data
            } else {
                Log.i(TAG, "load market data from LocalDataStorage")
                localDataStorage?.getMarketData()
            }
        }

        return null
    }

    private fun upDataSavedData(data: List<MarketsRecord>?) {
        deleteAllData()
        data?.let { saveMarketData(it) }
    }

    override fun saveMarketData(data: List<MarketsRecord>) {
        localDataStorage?.saveMarketData(data)
    }

    override fun deleteAllData() {
        localDataStorage?.deleteAllData()
    }


    override fun destroyInstance() {
        INSTANCE = null
    }
}