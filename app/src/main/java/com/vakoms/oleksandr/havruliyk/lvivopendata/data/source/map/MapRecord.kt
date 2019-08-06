package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map
import com.google.android.gms.maps.model.LatLng
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.AddressRecord

data class MapRecord(
    var adrress: AddressRecord,
    var latLng: LatLng
)