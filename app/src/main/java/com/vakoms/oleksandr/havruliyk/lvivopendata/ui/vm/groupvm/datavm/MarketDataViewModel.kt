package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketDataViewModel @Inject constructor(var repository: MarketRepository) : ViewModel() {
    private val recordId = MutableLiveData<Int>()
    val record: LiveData<MarketRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }
}