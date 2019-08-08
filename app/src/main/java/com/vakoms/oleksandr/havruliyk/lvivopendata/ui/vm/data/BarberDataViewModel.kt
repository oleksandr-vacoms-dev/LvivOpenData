package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromBarberRecord
import javax.inject.Inject

class BarberDataViewModel @Inject constructor(var repository: BarberRepository, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    val record: LiveData<BarberRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<BarberRecord>) {
        mapManager.addRecords(getAddressRecordFromBarberRecord(record))
    }
}