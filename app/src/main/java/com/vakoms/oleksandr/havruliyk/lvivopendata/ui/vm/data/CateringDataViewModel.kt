package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromCateringRecord
import javax.inject.Inject

class CateringDataViewModel @Inject constructor(var repository: CateringRepository, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    val record: LiveData<CateringRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<CateringRecord>) {
        mapManager.addRecords(getAddressRecordFromCateringRecord(record))
    }
}