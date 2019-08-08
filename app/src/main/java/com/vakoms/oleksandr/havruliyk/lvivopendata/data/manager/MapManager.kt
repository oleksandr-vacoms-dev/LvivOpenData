package com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapManager @Inject constructor() {

    private val addressRecords = MutableLiveData<List<AddressRecord>>()

    fun addRecords(records: List<AddressRecord>) {
        addressRecords.value = records
    }

    fun getAll() = addressRecords
}