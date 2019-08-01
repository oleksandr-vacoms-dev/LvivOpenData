package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse

class MarketViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<MarketsResponse>? = null
    private var remoteMarketDataStorage: RemoteMarketDataStorage? = null

    init {
        if (mutableLiveData == null) {
            remoteMarketDataStorage = RemoteMarketDataStorage.getInstance()
            mutableLiveData = remoteMarketDataStorage!!.getMarketData()
        }
    }

    fun getMarketsData(): LiveData<MarketsResponse>? {
        return mutableLiveData
    }

}