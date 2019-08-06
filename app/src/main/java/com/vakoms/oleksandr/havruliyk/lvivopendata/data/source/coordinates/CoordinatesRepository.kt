package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.coordinates

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.coordinates.CoordinatesRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.coordinates.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.CoordinatesDataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoordinatesRepository : CoordinatesDataStorage<CoordinatesRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getCoordinateByAddress(
        streetName: String,
        houseNumber: String
    ): MutableLiveData<List<CoordinatesRecord>> {
        val data = MutableLiveData<List<CoordinatesRecord>>()
        openDataApi.getCoordinatesByAddress(getCoordinatesSql(streetName, houseNumber))
            .enqueue(object : Callback<CoordinatesResponse> {
                override fun onResponse(
                    call: Call<CoordinatesResponse>,
                    response: Response<CoordinatesResponse>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body().result.records
                    }
                }

                override fun onFailure(call: Call<CoordinatesResponse>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }

    private fun getCoordinatesSql(streetName: String, houseNumber: String): String {
        return "SELECT * from \"8d10826b-c00d-4fbd-b196-e8a231b0f4c0\"" +
                " WHERE street_s_name LIKE '$streetName' and housenumber LIKE '$houseNumber'"
    }
}