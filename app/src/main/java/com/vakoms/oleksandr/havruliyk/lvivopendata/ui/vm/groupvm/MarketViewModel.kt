package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketViewModel @Inject constructor(repository: MarketRepository) : ViewModel() {

    var data: MutableLiveData<List<MarketRecord>> = repository.getAll() as MutableLiveData<List<MarketRecord>>

    fun getData(): LiveData<List<MarketRecord>> {
        return data
    }

}