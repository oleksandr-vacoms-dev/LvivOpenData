package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlBarberSearchByName
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
                .enqueue(BarberWebServiceCallback(it) { response, _ ->
                    insertItemsIntoDb(response, it)
                })
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: BarberRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getBarber(sqlBarberSearchByName(name, itemAtEnd._id))
                .enqueue(BarberWebServiceCallback(it) { response, _ ->
                    insertItemsIntoDb(response, it)
                })
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
}