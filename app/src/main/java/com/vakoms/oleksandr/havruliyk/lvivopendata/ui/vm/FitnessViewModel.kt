package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import javax.inject.Inject

class FitnessViewModel (fitnessRepository: FitnessRepository) : ViewModel() {
    private var fitnessLiveData = fitnessRepository.getFitnessData()

    fun getFitnessData(): LiveData<List<FitnessCentersRecord>>? {
        return fitnessLiveData
    }
}