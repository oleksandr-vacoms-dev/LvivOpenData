package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(database: ATMRoomDatabase) {

    private var atmDao: ATMDao = database.atmDao()

    fun getAll(): LiveData<List<ATMRecord>> {
        return atmDao.getAll()
    }

    fun saveAll(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    fun deleteAll() {
        atmDao.deleteAll()
    }

    fun getById(id: Int): LiveData<ATMRecord> = atmDao.getById(id)

    fun getByName(name: String): LiveData<List<ATMRecord>>? = atmDao.getByName(name)
}