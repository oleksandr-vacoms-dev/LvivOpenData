package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import javax.inject.Inject

class FitnessViewModel @Inject constructor(fitnessRepository: FitnessRepository) : ViewModel() {
    private var fitnessLiveData = fitnessRepository.getAllData()

    fun getFitnessData(): LiveData<List<FitnessRecord>>? {
        return fitnessLiveData
    }
}