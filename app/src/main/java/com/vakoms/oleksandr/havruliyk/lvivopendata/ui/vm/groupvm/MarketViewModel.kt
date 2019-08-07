package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketViewModel @Inject constructor(marketRepository: MarketRepository) : ViewModel() {

    val data: MutableLiveData<List<MarketRecord>> by lazy {
        MutableLiveData<List<MarketRecord>>().also {
            marketRepository.getAll()
        }
    }

    fun getData(): LiveData<List<MarketRecord>> {
        return data
    }

}