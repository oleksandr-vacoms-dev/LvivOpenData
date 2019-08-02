package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord


interface MarketDataStorage {

    fun getMarketData(): LiveData<List<MarketRecord>>?

    fun saveMarketData(data: List<MarketRecord>)

    fun deleteAllData()
}