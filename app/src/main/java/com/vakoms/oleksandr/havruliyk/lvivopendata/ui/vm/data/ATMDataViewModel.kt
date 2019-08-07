package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.getAddressRecordFromATMRecord
import javax.inject.Inject

class ATMDataViewModel @Inject constructor(var repository: ATMRepository, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    val record: LiveData<ATMRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<ATMRecord>) {
        mapManager.addRecords(getAddressRecordFromATMRecord(record))
    }
}