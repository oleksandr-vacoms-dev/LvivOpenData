package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.callback

import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.RefreshCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import retrofit2.Response
import java.util.concurrent.Executor

class MarketRefreshCallback(
    networkState: MutableLiveData<NetworkState>,
    ioExecutor: Executor,
    saveAllData: (Response<MarketsResponse>) -> Unit
) : RefreshCallback<MarketsResponse>(networkState, ioExecutor, saveAllData)