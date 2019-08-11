package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromFitnessRecord
import javax.inject.Inject

class FitnessViewModel @Inject constructor(var repository: FitnessRepository, var mapManager: MapManager) :
    ViewModel() {
    lateinit var data: MutableLiveData<List<FitnessRecord>>// = repository.getAll() as MutableLiveData<List<FitnessRecord>>

    private val searchString = MutableLiveData<String>()
    lateinit var searchData: LiveData<List<FitnessRecord>>// = Transformations.switchMap(searchString) { name -> repository.getByName(name)    }

    fun setSearchData(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<FitnessRecord>) {
        mapManager.addRecords(getAddressRecordFromFitnessRecord(record))
    }
}