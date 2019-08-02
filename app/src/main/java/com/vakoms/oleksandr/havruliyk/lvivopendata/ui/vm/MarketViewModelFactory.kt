package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.MarketRepository

class MarketViewModelFactory(private val repository: MarketRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarketViewModel::class.java)) {
            return MarketViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}