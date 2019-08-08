package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.MapRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import javax.inject.Inject

class MapViewModel @Inject constructor(var repository: MapRepository) : ViewModel() {
    private var data: MutableLiveData<MapRecord>? = repository.getMapRecords()

    fun getMapsRecords(): LiveData<MapRecord>? {
        return data
    }
}