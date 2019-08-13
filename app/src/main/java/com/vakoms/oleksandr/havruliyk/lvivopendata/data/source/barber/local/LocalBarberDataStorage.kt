package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(
    database: BarberRoomDatabase
) : DataStorage<BarberRecord> {

    private var barberDao = database.barberDao()

    override fun getListing() = Listing(
        factory = barberDao.getDataSourceFactory()
    )

    override fun getListingByName(name: String) = Listing(
        factory = barberDao.getDataSourceFactoryByName("%$name%")
    )

    override fun get(offset: Int, amount: Int) =
        barberDao.get(offset, amount)

    override fun getByName(name: String, offset: Int, amount: Int) =
        barberDao.getByName("%$name%")

    override fun save(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    override fun deleteAll() {
        barberDao.deleteAll()
    }

    override fun getById(id: Int) = barberDao.getById(id)
}