package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlCatering
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class CateringBoundaryCallback(
    private val webservice: OpenDataApi,
    private val handleResponse: (List<CateringRecord>) -> Unit,
    private val ioExecutor: Executor
) : DataBoundaryCallback<CateringRecord>(ioExecutor) {

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getCatering(sqlCatering(FIRST_ITEM))
                .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: CateringRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getCatering(sqlCatering(itemAtEnd._id))
                .enqueue(createWebserviceCallback(it))
        }
    }

    private fun insertItemsIntoDb(
        response: Response<CateringResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body().result.records)
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<CateringResponse> {
        return object : Callback<CateringResponse> {
            override fun onFailure(
                call: Call<CateringResponse>,
                t: Throwable
            ) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<CateringResponse>,
                response: Response<CateringResponse>
            ) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}