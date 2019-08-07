package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(database: ATMRoomDatabase) : DataStorage<ATMRecord> {

    private var atmDao: ATMDao = database.atmDao()

    override fun getAll(): LiveData<List<ATMRecord>> {
        return atmDao.getAll()
    }

    override fun saveAll(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    override fun deleteAll() {
        atmDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<ATMRecord> = atmDao.getById(id)
}