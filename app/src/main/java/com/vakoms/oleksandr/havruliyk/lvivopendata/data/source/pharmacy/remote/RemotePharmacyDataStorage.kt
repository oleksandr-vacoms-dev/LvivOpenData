package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.pharmacy.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemotePharmacyDataStorage : DataStorage<PharmacyRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAllData(): MutableLiveData<List<PharmacyRecord>> {
        val newsData = MutableLiveData<List<PharmacyRecord>>()
        openDataApi.getPharmacy().enqueue(object : Callback<PharmacyResponse> {
            override fun onResponse(
                call: Call<PharmacyResponse>,
                response: Response<PharmacyResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body().result.records
                }
                return
            }

            override fun onFailure(call: Call<PharmacyResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    override fun saveData(data: List<PharmacyRecord>) {

    }

    override fun deleteAllData() {

    }
}