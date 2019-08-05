package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalATMDataStorage(context: Context) : DataStorage<ATMRecord> {

    private var atmDao: ATMDao

    init {
        val roomDB: ATMRoomDatabase = Room.databaseBuilder(
            context,
            ATMRoomDatabase::class.java, "atm_db"
        )
            .allowMainThreadQueries()
            .build()

        atmDao = roomDB.atmDao()
    }

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