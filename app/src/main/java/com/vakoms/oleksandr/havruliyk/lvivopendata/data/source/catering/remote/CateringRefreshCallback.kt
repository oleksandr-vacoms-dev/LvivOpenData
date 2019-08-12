package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Response
import java.util.concurrent.Executor

class CateringRefreshCallback(
    networkState: MutableLiveData<NetworkState>,
    ioExecutor: Executor,
    saveAllData: (Response<CateringResponse>) -> Unit
) : RefreshCallback<CateringResponse>(networkState, ioExecutor, saveAllData)