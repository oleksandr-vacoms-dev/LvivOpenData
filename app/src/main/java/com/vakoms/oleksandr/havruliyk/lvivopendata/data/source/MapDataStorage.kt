package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRecord

interface MapDataStorage {
    fun getMapRecords(): MutableLiveData<MapRecord>
}