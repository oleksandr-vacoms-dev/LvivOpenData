package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import javax.inject.Inject

class BarberViewModel @Inject constructor(barberRepository: BarberRepository) : ViewModel() {
    private var barberData = barberRepository.getAll()

    fun getBarberData(): LiveData<List<BarberRecord>>? {
        return barberData
    }
}