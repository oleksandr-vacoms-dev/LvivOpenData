package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.annotation.MainThread
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlFitnessSearchByName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class FitnessByNameBoundaryCallback(
    private val webservice: OpenDataApi,
    private val handleResponse: (List<FitnessRecord>) -> Unit,
    private val ioExecutor: Executor,
    private val name: String
) : DataBoundaryCallback<FitnessRecord>(ioExecutor) {

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            webservice.getFitness(sqlFitnessSearchByName(name, FIRST_ITEM))
                .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: FitnessRecord) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            webservice.getFitness(sqlFitnessSearchByName(name, itemAtEnd.id))
                .enqueue(createWebserviceCallback(it))
        }
    }

    private fun insertItemsIntoDb(
        response: Response<FitnessResponse>,
        it: PagingRequestHelper.Request.Callback
    ) {
        ioExecutor.execute {
            handleResponse(response.body().result.records)
            it.recordSuccess()
        }
    }

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<FitnessResponse> {
        return object : Callback<FitnessResponse> {
            override fun onFailure(
                call: Call<FitnessResponse>,
                t: Throwable
            ) {
                it.recordFailure(t)
            }

            override fun onResponse(
                call: Call<FitnessResponse>,
                response: Response<FitnessResponse>
            ) {
                insertItemsIntoDb(response, it)
            }
        }
    }
}