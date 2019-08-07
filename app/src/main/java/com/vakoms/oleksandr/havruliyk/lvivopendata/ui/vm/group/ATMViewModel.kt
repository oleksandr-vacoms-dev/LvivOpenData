package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import javax.inject.Inject

class ATMViewModel @Inject constructor(repository: ATMRepository) : ViewModel() {
    var data: MutableLiveData<List<ATMRecord>> = repository.getAll() as MutableLiveData<List<ATMRecord>>

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<ATMRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }
}