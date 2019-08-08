package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map
import com.google.android.gms.maps.model.LatLng
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord

data class MapRecord(
    var address: AddressRecord,
    var latLng: LatLng
)