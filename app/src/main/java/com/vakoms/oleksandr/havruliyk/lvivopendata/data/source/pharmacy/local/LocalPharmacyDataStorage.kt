package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.pharmacy.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalPharmacyDataStorage(context: Context) : DataStorage<PharmacyRecord> {

    private var pharmacyDao: PharmacyDao

    init {
        val roomDB: PharmacyRoomDatabase = Room.databaseBuilder(
            context,
            PharmacyRoomDatabase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

        pharmacyDao = roomDB.pharmacyDao()
    }

    override fun getAllData(): LiveData<List<PharmacyRecord>> {
        return pharmacyDao.getAll()
    }

    override fun saveData(data: List<PharmacyRecord>) {
        pharmacyDao.insert(data)
    }

    override fun deleteAllData() {
        pharmacyDao.deleteAll()
    }
}