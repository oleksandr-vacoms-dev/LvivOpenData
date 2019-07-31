package com.vakoms.oleksandr.havruliyk.lvivopendata.data


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.remote.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.remote.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenDataRepository {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java)

    fun getMarketsData(): MutableLiveData<MarketsResponse> {
        val newsData = MutableLiveData<MarketsResponse>()
        openDataApi.getMarkets().enqueue(object : Callback<MarketsResponse> {
            override fun onResponse(
                call: Call<MarketsResponse>,
                response: Response<MarketsResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body()
                    Log.i(TAG, newsData.value.toString())
                }
            }

            override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    fun getFitnessCentersData(): MutableLiveData<FitnessCentersResponse> {
        val newsData = MutableLiveData<FitnessCentersResponse>()
        openDataApi.getFitnessCenters().enqueue(object : Callback<FitnessCentersResponse> {
            override fun onResponse(
                call: Call<FitnessCentersResponse>,
                response: Response<FitnessCentersResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body()
                    Log.i(TAG, newsData.value.toString())
                }
            }

            override fun onFailure(call: Call<FitnessCentersResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    companion object {
        const val TAG = "OpenDataRepository"

        private var openDataRepository: OpenDataRepository? = null

        fun getInstance(): OpenDataRepository? {
            if (openDataRepository == null) {
                openDataRepository = OpenDataRepository()
            }
            return openDataRepository
        }
    }
}