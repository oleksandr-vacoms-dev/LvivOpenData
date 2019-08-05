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
            FitnessRoomDatabase::class.java, "fitness_db"
        )
            .allowMainThreadQueries()
            .build()

        fitnessDao = roomDB.fitnessDao()
    }

    override fun getAll(): LiveData<List<FitnessRecord>> {
        return fitnessDao.getAll()
    }

    override fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAll() {
        fitnessDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<FitnessRecord> = fitnessDao.getById(id)
}