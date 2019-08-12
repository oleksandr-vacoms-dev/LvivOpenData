package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlBarberSearchByName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class BarberByNameBoundaryCallback(
    private val webservice: OpenDataApi,
    private val handleResponse: (List<BarberRecord>) -> Unit,
    private val ioExecutor: Executor,
    private val name: String
) : DataBoundaryCallback<BarberRecord>(ioExecutor) {

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getBarber(sqlBarberSearchByName(name, FIRST_ITEM))
                .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: BarberRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getBarber(sqlBarberSearchByName(name, itemAtEnd._id))
                .enqueue(createWebserviceCallback(it))
        }
    }

    private fun insertItemsIntoDb(
        response: Response<BarberResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body().result.records)
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<BarberResponse> {
        return object : Callback<BarberResponse> {
            override fun onFailure(
                call: Call<BarberResponse>,
                t: Throwable
            ) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<BarberResponse>,
                response: Response<BarberResponse>
            ) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}