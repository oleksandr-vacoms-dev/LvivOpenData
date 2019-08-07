package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import javax.inject.Inject

class FitnessViewModel @Inject constructor(repository: FitnessRepository) : ViewModel() {
    var data: MutableLiveData<List<FitnessRecord>> = repository.getAll() as MutableLiveData<List<FitnessRecord>>

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<FitnessRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }
}