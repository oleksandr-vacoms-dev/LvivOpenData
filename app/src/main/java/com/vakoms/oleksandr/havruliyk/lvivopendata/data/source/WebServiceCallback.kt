package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.paging.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class WebServiceCallback<T>(
    private val it: PagingRequestHelper.Request.Callback,
    private val insertIntoDb: (Response<T>, PagingRequestHelper.Request.Callback) -> Unit
) : Callback<T> {
    override fun onFailure(
        call: Call<T>,
        t: Throwable
    ) {
        it.recordFailure(t)
    }

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        insertIntoDb(response, it)
    }
}