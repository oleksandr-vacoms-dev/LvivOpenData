package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalBarberDataStorage(context: Context) : DataStorage<BarberRecord> {

    private var barberDao: BarberDao

    init {
        val roomDB: BarberRoomDatabase = Room.databaseBuilder(
            context,
            BarberRoomDatabase::class.java, "barber_db"
        )
            .allowMainThreadQueries()
            .build()

        barberDao = roomDB.barberDao()
    }

    override fun getAllData(): LiveData<List<BarberRecord>> {
        return barberDao.getAll()
    }

    override fun saveData(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    override fun deleteAllData() {
        barberDao.deleteAll()
    }
}
