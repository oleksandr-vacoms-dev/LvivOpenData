package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.MapDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getDefaultLatLnt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepository @Inject constructor(var openDataApi: OpenDataApi) : MapDataStorage {

    var addressRecords = listOf<AddressRecord>()

    override fun getMapRecords(): MutableLiveData<MapRecord> {
        val data = MutableLiveData<MapRecord>()

        for (address: AddressRecord in addressRecords) {
            openDataApi.getCoordinatesByAddress(getCoordinatesSql(address.streetName, address.buildingNumber))
                .enqueue(object : Callback<CoordinatesResponse> {
                    override fun onResponse(
                        call: Call<CoordinatesResponse>,
                        response: Response<CoordinatesResponse>
                    ) {
                        if (response.isSuccessful) {
                            data.value = MapRecord(
                                address,
                                getLatLng(response.body().result.records)
                            )
                        }
                    }

                    override fun onFailure(call: Call<CoordinatesResponse>, t: Throwable) {

                    }
                })
        }
        return data
    }

    private fun getLatLng(records: List<CoordinatesRecord>): LatLng {
        if (records.isNotEmpty()) {
            return with(records[0]) {
                LatLng(y, x)
            }
        }
        return getDefaultLatLnt()
    }

    private fun getCoordinatesSql(streetName: String, houseNumber: String): String {
        return "SELECT * from \"8d10826b-c00d-4fbd-b196-e8a231b0f4c0\"" +
                " WHERE (" +
                "((street_s_name LIKE '%$streetName') OR (street_d_name LIKE '%$streetName'))" +
                " AND housenumber LIKE '$houseNumber'" +
                ")"
    }
}