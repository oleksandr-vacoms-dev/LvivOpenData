package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteBarberDataStorage : DataStorage<BarberRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAll(): MutableLiveData<List<BarberRecord>> {
        val data = MutableLiveData<List<BarberRecord>>()
        openDataApi.getBarber().enqueue(object : Callback<BarberResponse> {
            override fun onResponse(
                call: Call<BarberResponse>,
                response: Response<BarberResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<BarberResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun saveAll(data: List<BarberRecord>) {

    }

    override fun deleteAll() {

    }
}