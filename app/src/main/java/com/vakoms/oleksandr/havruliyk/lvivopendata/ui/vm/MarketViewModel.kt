package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.MarketRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord

class MarketViewModel(application: Application) : AndroidViewModel(application) {
    private var liveData: LiveData<List<MarketsRecord>>? = null
    private lateinit var marketRepository: MarketRepository

    init {
        if (liveData == null) {
            marketRepository = MarketRepository.getInstance(application)!!
            liveData = marketRepository.getMarketData()
        }
    }

    fun getMarketsData(): LiveData<List<MarketsRecord>>? {
        return liveData
    }

}