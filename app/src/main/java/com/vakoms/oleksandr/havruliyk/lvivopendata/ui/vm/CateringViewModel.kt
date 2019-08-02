package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import javax.inject.Inject

class CateringViewModel @Inject constructor(cateringRepository: CateringRepository) : ViewModel() {
    private var cateringLiveData = cateringRepository.getAllData()

    fun getCateringData(): LiveData<List<CateringRecord>>? {
        return cateringLiveData
    }
}