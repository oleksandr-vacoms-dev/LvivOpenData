package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Response
import java.util.concurrent.Executor

class BarberRefreshCallback(
    networkState: MutableLiveData<NetworkState>,
    ioExecutor: Executor,
    saveAllData: (Response<BarberResponse>) -> Unit
) : RefreshCallback<BarberResponse>(networkState, ioExecutor, saveAllData)