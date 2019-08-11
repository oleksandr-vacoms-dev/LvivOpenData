package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(database: FitnessRoomDatabase):
    LocalDataStorage<FitnessRecord> {

    private var fitnessDao = database.fitnessDao()

    override fun getAll() = fitnessDao.getAll()

    override fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAll() {
        fitnessDao.deleteAll()
    }

    override fun getById(id: Int) = fitnessDao.getById(id)

    override fun getByName(name: String) = fitnessDao.getByName(name)
}