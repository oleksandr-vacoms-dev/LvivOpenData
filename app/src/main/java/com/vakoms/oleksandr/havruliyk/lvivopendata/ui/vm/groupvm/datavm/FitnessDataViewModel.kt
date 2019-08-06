package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import javax.inject.Inject

class FitnessDataViewModel @Inject constructor(var repository: FitnessRepository) : ViewModel() {
    private var data: LiveData<FitnessRecord>? = null

    fun getFitnessDataById(dataId: Int): LiveData<FitnessRecord>? {
        data = repository.getById(dataId)
        return data
    }
}