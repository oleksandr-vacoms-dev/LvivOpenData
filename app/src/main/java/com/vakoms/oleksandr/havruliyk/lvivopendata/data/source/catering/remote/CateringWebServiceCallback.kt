package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote

import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.WebServiceCallback
import retrofit2.Response

class CateringWebServiceCallback(
    it: PagingRequestHelper.Request.Callback,
    insertIntoDb: (Response<CateringResponse>, PagingRequestHelper.Request.Callback) -> Unit
) : WebServiceCallback<CateringResponse>(it, insertIntoDb)