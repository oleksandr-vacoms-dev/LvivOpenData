package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromCateringRecord
import javax.inject.Inject

class CateringDescriptionViewModel @Inject constructor(var repository: LocalCateringDataStorage, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    var record: LiveData<CateringRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<CateringRecord>) {
        mapManager.addRecords(getAddressRecordFromCateringRecord(record))
    }
}