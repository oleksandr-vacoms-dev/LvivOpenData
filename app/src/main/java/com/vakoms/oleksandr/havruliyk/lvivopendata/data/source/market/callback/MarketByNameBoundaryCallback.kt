package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.callback

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlMarketsSearchByName
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
                .enqueue(MarketWebServiceCallback(it) { response, _ ->
                    insertItemsIntoDb(response, it)
                })
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: MarketRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getMarkets(sqlMarketsSearchByName(name, itemAtEnd.id))
                .enqueue(MarketWebServiceCallback(it) { response, _ ->
                    insertItemsIntoDb(response, it)
                })
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
}