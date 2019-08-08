package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromFitnessRecord
import javax.inject.Inject

class FitnessDataViewModel @Inject constructor(var repository: FitnessRepository, var mapManager: MapManager) : ViewModel() {

    private val recordId = MutableLiveData<Int>()
    val record: LiveData<FitnessRecord> = Transformations.switchMap(recordId) { id -> repository.getById(id) }

    fun setRecordId(resourceId: Int) {
        this.recordId.value = resourceId
    }

    fun addRecordsToMap(record: List<FitnessRecord>){
        mapManager.addRecords(getAddressRecordFromFitnessRecord(record))
    }
}