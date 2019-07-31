package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.OpenDataRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse

class MarketViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<MarketsResponse>? = null
    private var openDataRepository: OpenDataRepository? = null

    init {
        if (mutableLiveData == null) {
            openDataRepository = OpenDataRepository.getInstance()
            mutableLiveData = openDataRepository!!.getMarketsData()
        }
    }

    fun getMarketsData(): LiveData<MarketsResponse>? {
        return mutableLiveData
    }

}