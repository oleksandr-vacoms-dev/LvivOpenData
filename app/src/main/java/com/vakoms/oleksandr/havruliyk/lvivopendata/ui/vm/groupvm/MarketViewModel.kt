package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketViewModel @Inject constructor(marketRepository: MarketRepository) : ViewModel() {
    private var marketData = marketRepository.getAll()!!

    fun getMarketData(): LiveData<List<MarketRecord>>? {
        return marketData
    }
}