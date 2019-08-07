package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(database: BarberRoomDatabase) : DataStorage<BarberRecord> {

    private var barberDao: BarberDao = database.barberDao()

    override fun getAll(): LiveData<List<BarberRecord>> {
        return barberDao.getAll()
    }

    override fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    override fun deleteAll() {
        barberDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<BarberRecord> = barberDao.getById(id)

    override fun getByName(name: String): LiveData<List<BarberRecord>>? = barberDao.getByName(name)
}