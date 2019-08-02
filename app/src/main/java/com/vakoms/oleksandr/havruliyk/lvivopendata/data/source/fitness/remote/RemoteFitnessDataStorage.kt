package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteFitnessDataStorage : DataStorage<FitnessRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAllData(): MutableLiveData<List<FitnessRecord>> {
        val newsData = MutableLiveData<List<FitnessRecord>>()
        openDataApi.getFitnessCenters().enqueue(object : Callback<FitnessResponse> {
            override fun onResponse(
                call: Call<FitnessResponse>,
                response: Response<FitnessResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body().result.records
                }
                return
            }

            override fun onFailure(call: Call<FitnessResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    override fun saveData(data: List<FitnessRecord>) {

    }

    override fun deleteAllData() {

    }
}