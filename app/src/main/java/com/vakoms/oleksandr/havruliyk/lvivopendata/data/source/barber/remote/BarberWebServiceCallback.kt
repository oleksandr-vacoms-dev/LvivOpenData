package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.WebServiceCallback
import retrofit2.Response

class BarberWebServiceCallback(
    it: PagingRequestHelper.Request.Callback,
    insertIntoDb: (Response<BarberResponse>, PagingRequestHelper.Request.Callback) -> Unit
) : WebServiceCallback<BarberResponse>(it, insertIntoDb)