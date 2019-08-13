package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromFitnessRecord
import javax.inject.Inject

class FitnessDescriptionViewModel @Inject constructor(var repository: LocalFitnessDataStorage, var mapManager: MapManager) :
    ViewModel() {

    private val recordId = MutableLiveData<Int>()
    var record: LiveData<FitnessRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<FitnessRecord>) {
        mapManager.addRecords(getAddressRecordFromFitnessRecord(record))
    }
}