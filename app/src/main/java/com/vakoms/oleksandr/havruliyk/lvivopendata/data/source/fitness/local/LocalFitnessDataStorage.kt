package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(database: FitnessRoomDatabase) : DataStorage<FitnessRecord> {

    private var fitnessDao: FitnessDao = database.fitnessDao()

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