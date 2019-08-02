package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalFitnessDataStorage(context: Context) : DataStorage<FitnessRecord> {

    private var fitnessDao: FitnessDao

    init {
        val roomDB: FitnessRoomDatabase = Room.databaseBuilder(
            context,
            FitnessRoomDatabase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

        fitnessDao = roomDB.fitnessDao()
    }

    override fun getAllData(): LiveData<List<FitnessRecord>> {
        return fitnessDao.getAll()
    }

    override fun saveData(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAllData() {
        fitnessDao.deleteAll()
    }
}
