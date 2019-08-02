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

    override fun getAllData(): MutableLiveData<List<BarberRecord>> {
        val newsData = MutableLiveData<List<BarberRecord>>()
        openDataApi.getBarber().enqueue(object : Callback<BarberResponse> {
            override fun onResponse(
                call: Call<BarberResponse>,
                response: Response<BarberResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body().result.records
                }
                return
            }

            override fun onFailure(call: Call<BarberResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    override fun saveData(data: List<BarberRecord>) {

    }

    override fun deleteAllData() {

    }
}