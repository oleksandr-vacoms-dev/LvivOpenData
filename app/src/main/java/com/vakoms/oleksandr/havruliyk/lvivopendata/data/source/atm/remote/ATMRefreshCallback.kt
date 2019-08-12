package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Response
import java.util.concurrent.Executor

class ATMRefreshCallback(
    networkState: MutableLiveData<NetworkState>,
    ioExecutor: Executor,
    saveAllData: (Response<ATMResponse>) -> Unit
) : RefreshCallback<ATMResponse>(networkState, ioExecutor, saveAllData)