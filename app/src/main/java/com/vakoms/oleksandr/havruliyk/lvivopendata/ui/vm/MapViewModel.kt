package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.MapDataStorage
import javax.inject.Inject

class MapViewModel @Inject constructor(var repository: MapDataStorage) : ViewModel() {
    private var data: MutableLiveData<MapRecord>? = repository.getMapRecords()

    fun getMapsRecords(): LiveData<MapRecord>? {
        return data
    }
}