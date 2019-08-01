package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

class FitnessViewModel(application: Application) : AndroidViewModel(application) {
    private var mutableLiveDataFitness: MutableLiveData<List<FitnessCentersRecord>>? = null
    private var fitnessRepository: FitnessRepository? = null

    init {
        if (mutableLiveDataFitness == null) {
            fitnessRepository = FitnessRepository.getInstance(application)
            mutableLiveDataFitness = fitnessRepository?.getFitnessData()
        }
    }

    fun getFitnessData(): LiveData<List<FitnessCentersRecord>>? {
        return mutableLiveDataFitness
    }
}