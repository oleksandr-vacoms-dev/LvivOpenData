package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

class FitnessViewModel(application: Application) : AndroidViewModel(application) {
    private var fitnessLiveData: LiveData<List<FitnessCentersRecord>>? = null
    private lateinit var fitnessRepository: FitnessRepository

    init {
        if (fitnessLiveData == null) {
            fitnessRepository = FitnessRepository.getInstance(application)!!
            fitnessLiveData = fitnessRepository.getFitnessData()
        }
    }

    fun getFitnessData(): LiveData<List<FitnessCentersRecord>>? {
        return fitnessLiveData
    }
}