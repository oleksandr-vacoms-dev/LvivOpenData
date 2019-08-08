package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.AddressRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.MapRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.MapDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.coordinatesSql
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getLatLng
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
            openDataApi.getCoordinatesByAddress(coordinatesSql(address.streetName, address.buildingNumber))
                .enqueue(object : Callback<CoordinatesResponse> {
                    override fun onResponse(
                        call: Call<CoordinatesResponse>,
                        response: Response<CoordinatesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val coordinates = response.body().result.records
                            data.value = MapRecord(
                                address,
                                coordinates.getLatLng()
                            )
                        }
                    }

                    override fun onFailure(call: Call<CoordinatesResponse>, t: Throwable) {

                    }
                })
        }
        return data
    }
}