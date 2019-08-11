package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(database: FitnessRoomDatabase)  {

    private var fitnessDao: FitnessDao = database.fitnessDao()

     fun getAll(): LiveData<List<FitnessRecord>> {
        return fitnessDao.getAll()
    }

     fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

     fun deleteAll() {
        fitnessDao.deleteAll()
    }

     fun getById(id: Int): LiveData<FitnessRecord> = fitnessDao.getById(id)

     fun getByName(name: String): LiveData<List<FitnessRecord>>? = fitnessDao.getByName(name)

}