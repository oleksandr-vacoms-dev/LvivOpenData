package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import javax.inject.Inject

class ATMViewModel @Inject constructor(repository: ATMRepository) : ViewModel() {
    private var data = repository.getAll()!!

    fun getATMData(): LiveData<List<ATMRecord>>? {
        return data
    }
}