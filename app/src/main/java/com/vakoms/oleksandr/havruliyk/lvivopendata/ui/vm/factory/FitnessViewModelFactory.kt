package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.FitnessViewModel


class FitnessViewModelFactory(private val repository: FitnessRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FitnessViewModel::class.java)) {
            return FitnessViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}