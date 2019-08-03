package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import javax.inject.Inject

class ATMViewModel @Inject constructor(ATMRepository: ATMRepository) : ViewModel() {
    private var atmData = ATMRepository.getAllData()!!

    fun getPharmacyData(): LiveData<List<ATMRecord>>? {
        return atmData
    }
}