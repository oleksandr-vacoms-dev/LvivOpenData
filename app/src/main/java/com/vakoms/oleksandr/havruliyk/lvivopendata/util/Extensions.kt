package com.vakoms.oleksandr.havruliyk.lvivopendata.util

import com.google.android.gms.maps.model.LatLng
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesRecord

fun List<CoordinatesRecord>.getLatLng(): LatLng {
    return if (isNotEmpty()) {
        with(this[0]){ LatLng(y, x) }
    } else {
        getDefaultLatLnt()
    }
}