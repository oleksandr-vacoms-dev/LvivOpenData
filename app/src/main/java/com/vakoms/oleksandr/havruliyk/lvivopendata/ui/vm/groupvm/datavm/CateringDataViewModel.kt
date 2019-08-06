package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import javax.inject.Inject

class CateringDataViewModel @Inject constructor(var repository: CateringRepository) : ViewModel() {
    private var data: LiveData<CateringRecord>? = null

    fun getCateringDataById(dataId: Int): LiveData<CateringRecord>? {
        data = repository.getById(dataId)
        return data
    }
}