package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

abstract class RefreshCallback<T>(
    private val networkState: MutableLiveData<NetworkState>,
    private val ioExecutor: Executor,
    private val saveAllData: (Response<T>) -> Unit
) : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        networkState.value = NetworkState.error(t.message)
    }

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        ioExecutor.execute {
            saveAllData(response)
            networkState.postValue(NetworkState.LOADED)
        }
    }
}