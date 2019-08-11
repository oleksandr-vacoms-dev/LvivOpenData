package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import android.util.Log
import androidx.annotation.MainThread
import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class MarketBoundaryCallback(
    private val webservice: OpenDataApi,
    private val handleResponse: (List<MarketRecord>) -> Unit,
    private val ioExecutor: Executor
) : PagedList.BoundaryCallback<MarketRecord>() {

    val helper = PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getMarkets(getSqlOrderedById(MARKET_ID, FIRST_ITEM, PAGE_SIZE))
                .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: MarketRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getMarkets(getSqlOrderedById(MARKET_ID, itemAtEnd.id, PAGE_SIZE))
                .enqueue(createWebserviceCallback(it))
            Log.d("MarketBoundaryCallback", "onItemAtEndLoaded -> ${itemAtEnd.id}")
        }
    }

    private fun insertItemsIntoDb(
        response: Response<MarketsResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body().result.records)
            Log.d("MarketBoundaryCallback", "handleResponce -> ${response.body().result.records}")
            it.recordSuccess()
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: MarketRecord) {
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<MarketsResponse> {
        return object : Callback<MarketsResponse> {
            override fun onFailure(
                call: Call<MarketsResponse>,
                t: Throwable
            ) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<MarketsResponse>,
                response: Response<MarketsResponse>
            ) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}