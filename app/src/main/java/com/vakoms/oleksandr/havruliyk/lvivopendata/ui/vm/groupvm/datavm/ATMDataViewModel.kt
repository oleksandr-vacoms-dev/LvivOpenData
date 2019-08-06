package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import javax.inject.Inject

class ATMDataViewModel @Inject constructor(var repository: ATMRepository) : ViewModel() {
    private var data: LiveData<ATMRecord>? = null

    fun getATMDataById(dataId: Int): LiveData<ATMRecord>? {
        data = repository.getById(dataId)
        return data
    }
}