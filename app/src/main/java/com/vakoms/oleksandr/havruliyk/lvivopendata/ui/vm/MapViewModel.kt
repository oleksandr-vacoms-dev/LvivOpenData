package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.MapRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import javax.inject.Inject

class MapViewModel @Inject constructor(var mapManager: MapManager, var repository: MapRepository) : ViewModel() {
    private val addressRecords = mapManager.getAll()
    var mapRecords: LiveData<List<MapRecord>> = Transformations.switchMap(addressRecords) { addresses ->
        repository.getMapRecordsByAddressRecords(addresses)
    }
}