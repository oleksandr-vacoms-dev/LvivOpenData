package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(database: ATMRoomDatabase) :
    LocalDataStorage<ATMRecord> {

    private var atmDao = database.atmDao()

    override fun getAll() = atmDao.getAll()

    override fun saveAll(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    override fun deleteAll() {
        atmDao.deleteAll()
    }

    override fun getById(id: Int) = atmDao.getById(id)

    override fun getByName(name: String) = atmDao.getByName(name)
}