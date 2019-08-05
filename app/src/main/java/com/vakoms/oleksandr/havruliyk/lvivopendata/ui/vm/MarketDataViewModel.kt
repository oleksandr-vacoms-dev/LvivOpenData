package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketDataViewModel @Inject constructor(var repository: MarketRepository) : ViewModel() {
    private var data: LiveData<MarketRecord>? = null

    fun getMarketDataById(dataId: Int): LiveData<MarketRecord>? {
        data = repository.getById(dataId)
        return data
    }
}