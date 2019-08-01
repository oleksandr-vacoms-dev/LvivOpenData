package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse


interface MarketDataStorage {

    fun getMarketData(): MutableLiveData<List<MarketsRecord>>

    fun saveMarketData(data: List<MarketsRecord>)

    fun destroyInstance()
}