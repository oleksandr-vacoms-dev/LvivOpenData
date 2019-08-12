package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.callback

import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.WebServiceCallback
import retrofit2.Response

class MarketWebServiceCallback(
    it: PagingRequestHelper.Request.Callback,
    insertIntoDb: (Response<MarketsResponse>, PagingRequestHelper.Request.Callback) -> Unit
) : WebServiceCallback<MarketsResponse>(it, insertIntoDb)