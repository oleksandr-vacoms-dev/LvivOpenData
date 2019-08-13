package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromMarketRecord
import javax.inject.Inject

class MarketDescriptionViewModel @Inject constructor(var repository: LocalMarketDataStorage, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    var record: LiveData<MarketRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<MarketRecord>) {
        mapManager.addRecords(getAddressRecordFromMarketRecord(record))
    }
}