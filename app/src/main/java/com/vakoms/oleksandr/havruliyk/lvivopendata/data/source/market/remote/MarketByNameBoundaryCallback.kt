package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlMarketsSearchByName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class MarketByNameBoundaryCallback(
    private val webservice: OpenDataApi,
    private val handleResponse: (List<MarketRecord>) -> Unit,
    private val ioExecutor: Executor,
    private val name: String
) : DataBoundaryCallback<MarketRecord>(ioExecutor) {

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getMarkets(sqlMarketsSearchByName(name, FIRST_ITEM))
                .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: MarketRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getMarkets(sqlMarketsSearchByName(name, itemAtEnd.id))
                .enqueue(createWebserviceCallback(it))
        }
    }

    private fun insertItemsIntoDb(
        response: Response<MarketsResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body().result.records)
            it.recordSuccess()
        }
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