package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalFitnessDataStorage(context: Context) : DataStorage<FitnessCentersRecord> {

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

    override fun getAllData(): LiveData<List<FitnessCentersRecord>> {
        return fitnessDao.getAll()
    }

    override fun saveData(data: List<FitnessCentersRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAllData() {
        fitnessDao.deleteAll()
    }
}
