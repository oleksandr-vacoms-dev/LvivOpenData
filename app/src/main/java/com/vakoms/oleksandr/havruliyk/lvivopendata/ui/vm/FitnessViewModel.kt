package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse

class FitnessViewModel : ViewModel() {

    private var mutableLiveDataFitness: MutableLiveData<FitnessCentersResponse>? = null
    private var remoteFitnessDataStorage: RemoteFitnessDataStorage? = null

    init {
        if (mutableLiveDataFitness == null) {
            remoteFitnessDataStorage = RemoteFitnessDataStorage.getInstance()
            mutableLiveDataFitness = remoteFitnessDataStorage!!.getFitnessData()
        }
    }

    fun getFitnessData(): LiveData<FitnessCentersResponse>? {
        return mutableLiveDataFitness
    }
}