package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(database: BarberRoomDatabase) :
    LocalDataStorage<BarberRecord> {

    private var barberDao = database.barberDao()

    override fun getAll() = barberDao.getAll()

    override fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    override fun deleteAll() {
        barberDao.deleteAll()
    }

    override fun getById(id: Int) = barberDao.getById(id)

    override fun getByName(name: String) = barberDao.getByName(name)
}