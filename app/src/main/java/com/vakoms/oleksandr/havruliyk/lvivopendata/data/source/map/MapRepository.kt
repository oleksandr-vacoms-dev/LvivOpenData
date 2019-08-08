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

    override fun getMapRecordsByAddressRecords(addressRecords: List<AddressRecord>): MutableLiveData<List<MapRecord>> {
        val mapRecords = mutableListOf<MapRecord>()
        val data = MutableLiveData<List<MapRecord>>()

        for (address: AddressRecord in addressRecords) {
            openDataApi.getCoordinatesByAddress(coordinatesSql(address.streetName, address.buildingNumber))
                .enqueue(object : Callback<CoordinatesResponse> {
                    override fun onResponse(
                        call: Call<CoordinatesResponse>,
                        response: Response<CoordinatesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val coordinates = response.body().result.records
                            mapRecords.add(MapRecord(address, coordinates.getLatLng()))
                            if (addressRecords.indexOf(address) == addressRecords.lastIndex) {
                                data.value = mapRecords
                            }
                        }
                    }

                    override fun onFailure(call: Call<CoordinatesResponse>, t: Throwable) {

                    }
                })
        }
        return data
    }
}