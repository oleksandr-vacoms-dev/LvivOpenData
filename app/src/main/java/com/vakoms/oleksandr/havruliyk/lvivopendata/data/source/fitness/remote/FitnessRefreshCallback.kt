package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Response
import java.util.concurrent.Executor

class FitnessRefreshCallback(
    networkState: MutableLiveData<NetworkState>,
    ioExecutor: Executor,
    saveAllData: (Response<FitnessResponse>) -> Unit
) : RefreshCallback<FitnessResponse>(networkState, ioExecutor, saveAllData)