package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(
    database: ATMRoomDatabase
) : DataStorage<ATMRecord> {

    private var atmDao = database.atmDao()

    override fun getListing() = Listing(
        factory = atmDao.getDataSourceFactory()
    )

    override fun getListingByName(name: String) = Listing(
        factory = atmDao.getDataSourceFactoryByName("%$name%")
    )

    override fun get(offset: Int, amount: Int) =
        atmDao.get(offset, amount)

    override fun getByName(name: String, offset: Int, amount: Int) =
        atmDao.getByName("%$name%")

    override fun save(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    override fun deleteAll() {
        atmDao.deleteAll()
    }

    override fun getById(id: Int) = atmDao.getById(id)
}