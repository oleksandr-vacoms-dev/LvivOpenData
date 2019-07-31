package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.OpenDataRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse

class FitnessViewModel : ViewModel() {

    private var mutableLiveDataFitness: MutableLiveData<FitnessCentersResponse>? = null
    private var openDataRepository: OpenDataRepository? = null

    init {
        if (mutableLiveDataFitness == null) {
            openDataRepository = OpenDataRepository.getInstance()
            mutableLiveDataFitness = openDataRepository!!.getFitnessCentersData()
        }
    }

    fun getFitnessData(): LiveData<FitnessCentersResponse>? {
        return mutableLiveDataFitness
    }
}