package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import javax.inject.Inject

class BarberDataViewModel @Inject constructor(var repository: BarberRepository) : ViewModel() {
    private var data: LiveData<BarberRecord>? = null

    fun getBarberDataById(dataId: Int): LiveData<BarberRecord>? {
        data = repository.getById(dataId)
        return data
    }
}