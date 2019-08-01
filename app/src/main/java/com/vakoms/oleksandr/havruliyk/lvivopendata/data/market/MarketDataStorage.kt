package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord


interface MarketDataStorage {

    fun getMarketData(): LiveData<List<MarketsRecord>>?

    fun saveMarketData(data: List<MarketsRecord>)

    fun deleteAllData()

    fun destroyInstance()
}