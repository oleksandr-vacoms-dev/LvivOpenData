package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromBarberRecord
import javax.inject.Inject

class BarberViewModel @Inject constructor(var repository: BarberRepository, var mapManager: MapManager) : ViewModel() {
    var data: MutableLiveData<List<BarberRecord>> = repository.getAll() as MutableLiveData<List<BarberRecord>>

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<BarberRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<BarberRecord>) {
        mapManager.addRecords(getAddressRecordFromBarberRecord(record))
    }
}