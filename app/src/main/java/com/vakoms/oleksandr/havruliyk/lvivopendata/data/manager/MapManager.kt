package com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapManager @Inject constructor(var mapRepository: MapRepository) {

    fun addRecords(records: List<AddressRecord>) {
        mapRepository.addressRecords = records
    }
}