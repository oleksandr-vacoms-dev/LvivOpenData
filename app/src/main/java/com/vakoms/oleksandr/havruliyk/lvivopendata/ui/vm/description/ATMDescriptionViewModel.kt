package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromATMRecord
import javax.inject.Inject

class ATMDescriptionViewModel @Inject constructor(var repository: LocalATMDataStorage, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    var record: LiveData<ATMRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<ATMRecord>) {
        mapManager.addRecords(getAddressRecordFromATMRecord(record))
    }
}