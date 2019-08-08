package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.MapRecord

interface MapDataStorage {
    fun getMapRecordsByAddressRecords(addressRecords: List<AddressRecord>): MutableLiveData<List<MapRecord>>
}