package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.WebServiceCallback
import retrofit2.Response

class FitnessWebServiceCallback(
    it: PagingRequestHelper.Request.Callback,
    insertIntoDb: (Response<FitnessResponse>, PagingRequestHelper.Request.Callback) -> Unit
) : WebServiceCallback<FitnessResponse>(it, insertIntoDb)