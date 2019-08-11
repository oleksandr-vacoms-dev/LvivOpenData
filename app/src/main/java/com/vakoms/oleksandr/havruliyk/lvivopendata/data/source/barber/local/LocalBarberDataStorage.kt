package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(database: BarberRoomDatabase) {

    private var barberDao: BarberDao = database.barberDao()

    fun getAll(): LiveData<List<BarberRecord>> {
        return barberDao.getAll()
    }

    fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    fun deleteAll() {
        barberDao.deleteAll()
    }

    fun getById(id: Int): LiveData<BarberRecord> = barberDao.getById(id)

    fun getByName(name: String): LiveData<List<BarberRecord>>? = barberDao.getByName(name)
}