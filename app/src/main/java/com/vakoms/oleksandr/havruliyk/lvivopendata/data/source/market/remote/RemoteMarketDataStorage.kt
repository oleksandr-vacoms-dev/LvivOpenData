package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote


import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.RetrofitService
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMarketDataStorage : DataStorage<MarketRecord> {

    private val openDataApi: OpenDataApi = RetrofitService.createService(
        OpenDataApi::class.java
    )

    override fun getAll(): MutableLiveData<List<MarketRecord>> {
        val data = MutableLiveData<List<MarketRecord>>()
        openDataApi.getMarkets().enqueue(object : Callback<MarketsResponse> {
            override fun onResponse(
                call: Call<MarketsResponse>,
                response: Response<MarketsResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun saveAll(data: List<MarketRecord>) {

    }

    override fun deleteAll() {

    }
}