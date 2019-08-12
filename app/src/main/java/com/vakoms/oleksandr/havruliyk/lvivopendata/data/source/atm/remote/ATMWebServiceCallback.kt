package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote

import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.WebServiceCallback
import retrofit2.Response

class ATMWebServiceCallback(
    it: PagingRequestHelper.Request.Callback,
    insertIntoDb: (Response<ATMResponse>, PagingRequestHelper.Request.Callback) -> Unit
) : WebServiceCallback<ATMResponse>(it, insertIntoDb)