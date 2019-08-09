package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RemoteDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source.MarketDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.marketSql
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
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

//    fun getAll_(): Listing<MarketRecord> = with(
//            MarketDataSourceFactory(
//                openDataApi,
//                networkExecutor
//            )
//        ) {
//            Listing(
//                pagedList = LivePagedListBuilder(this, pagedListConfig())
//                    .setFetchExecutor(networkExecutor)
//                    .build(),
//                networkState = Transformations.switchMap(source) { it.network },
//                retry = { source.value?.retryAllFailed() },
//                refresh = { source.value?.invalidate() },
//                refreshState = Transformations.switchMap(source) { it.initial })
//        }
//    }

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