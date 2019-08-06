package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.coordinates.CoordinatesRecord

interface CoordinatesDataStorage<T> {

    fun getCoordinateByAddress(
        streetName: String,
        houseNumber: String
    ): MutableLiveData<List<CoordinatesRecord>>
}