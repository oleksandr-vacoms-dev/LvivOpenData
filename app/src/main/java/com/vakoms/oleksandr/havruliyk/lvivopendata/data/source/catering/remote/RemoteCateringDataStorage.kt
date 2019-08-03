package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteCateringDataStorage : DataStorage<CateringRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAll(): MutableLiveData<List<CateringRecord>> {
        val data = MutableLiveData<List<CateringRecord>>()
        openDataApi.getCatering().enqueue(object : Callback<CateringResponse> {
            override fun onResponse(
                call: Call<CateringResponse>,
                response: Response<CateringResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<CateringResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun saveAll(data: List<CateringRecord>) {

    }

    override fun deleteAll() {

    }
}