package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteFitnessDataStorage : DataStorage<FitnessCentersRecord> {

    companion object {
        const val TAG = "RemoteFitnessDataStorage"
    }

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAllData(): MutableLiveData<List<FitnessCentersRecord>> {
        val newsData = MutableLiveData<List<FitnessCentersRecord>>()
        openDataApi.getFitnessCenters().enqueue(object : Callback<FitnessCentersResponse> {
            override fun onResponse(
                call: Call<FitnessCentersResponse>,
                response: Response<FitnessCentersResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body().result.records
                    Log.i(RemoteMarketDataStorage.TAG, newsData.value.toString())
                }
                return
            }

            override fun onFailure(call: Call<FitnessCentersResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    override fun saveData(data: List<FitnessCentersRecord>) {

    }

    override fun deleteAllData() {

    }
}