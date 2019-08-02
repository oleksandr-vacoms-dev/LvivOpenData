package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMarketDataStorage : MarketDataStorage {

    companion object {
        const val TAG = "RemoteMarketDataStorage"
    }

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getMarketData(): MutableLiveData<List<MarketRecord>> {
        val newsData = MutableLiveData<List<MarketRecord>>()
        openDataApi.getMarkets().enqueue(object : Callback<MarketsResponse> {
            override fun onResponse(
                call: Call<MarketsResponse>,
                response: Response<MarketsResponse>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body().result.records
                    Log.i(TAG, newsData.value.toString())
                }
            }

            override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

    override fun saveMarketData(data: List<MarketRecord>) {

    }

    override fun deleteAllData() {

    }
}