package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketViewModel @Inject constructor(marketRepository: MarketRepository) : ViewModel() {
    private var marketLiveData = marketRepository.getMarketData()!!

    fun getMarketsData(): LiveData<List<MarketRecord>>? {
        return marketLiveData
    }
}