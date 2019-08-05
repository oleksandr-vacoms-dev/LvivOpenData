package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import javax.inject.Inject

class ATMDataViewModel @Inject constructor(var atmRepository: ATMRepository) : ViewModel() {
    private var atmData: LiveData<ATMRecord>? = null

    fun getATMDataById(dataId: Int): LiveData<ATMRecord>? {
        atmData = atmRepository.getById(dataId)
        return atmData
    }
}