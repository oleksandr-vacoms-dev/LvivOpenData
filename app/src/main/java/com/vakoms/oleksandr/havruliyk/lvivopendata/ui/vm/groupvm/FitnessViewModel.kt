package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import javax.inject.Inject

class FitnessViewModel @Inject constructor(fitnessRepository: FitnessRepository) : ViewModel() {
    private var fitnessData = fitnessRepository.getAll()

    fun getFitnessData(): LiveData<List<FitnessRecord>>? {
        return fitnessData
    }
}