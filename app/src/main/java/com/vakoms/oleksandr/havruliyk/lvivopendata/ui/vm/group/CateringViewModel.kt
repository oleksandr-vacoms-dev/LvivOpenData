package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromCateringRecord
import javax.inject.Inject

class CateringViewModel @Inject constructor(var repository: CateringRepository, var mapManager: MapManager) : ViewModel() {
    lateinit var data: MutableLiveData<List<CateringRecord>>//= repository.getAll() as MutableLiveData<List<CateringRecord>>

    private val searchString = MutableLiveData<String>()
    lateinit var searchData: LiveData<List<CateringRecord>>// = Transformations.switchMap(searchString) { name -> repository.getByName(name) }

    fun setSearchData(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<CateringRecord>){
        mapManager.addRecords(getAddressRecordFromCateringRecord(record))
    }
}