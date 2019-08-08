package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RemoteDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.marketSql
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteMarketDataStorage @Inject constructor(var openDataApi: OpenDataApi) : RemoteDataStorage<MarketRecord>() {

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
                data.value = listOf()
            }
        })
        return data
    }

    override fun getByName(name: String): LiveData<List<MarketRecord>>? {
        val data = MutableLiveData<List<MarketRecord>>()
        openDataApi.getMarketByName(marketSql(name)).enqueue(object : Callback<MarketsResponse> {
            override fun onResponse(
                call: Call<MarketsResponse>,
                response: Response<MarketsResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<MarketsResponse>, t: Throwable) {
                data.value = listOf()
            }
        })
        return data
    }
}