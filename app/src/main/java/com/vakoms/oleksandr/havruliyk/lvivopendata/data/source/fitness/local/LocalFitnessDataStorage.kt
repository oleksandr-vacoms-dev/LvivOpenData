package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(
    database: FitnessRoomDatabase
) : DataStorage<FitnessRecord> {

    private var fitnessDao = database.fitnessDao()

    override fun getListing() = Listing(
        factory = fitnessDao.getDataSourceFactory()
    )

    override fun getListingByName(name: String) = Listing(
        factory = fitnessDao.getDataSourceFactoryByName("%$name%")
    )

    override fun get(offset: Int, amount: Int) =
        fitnessDao.get(offset, amount)

    override fun getByName(name: String, offset: Int, amount: Int) =
        fitnessDao.getByName("%$name%")

    override fun save(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAll() {
        fitnessDao.deleteAll()
    }

    override fun getById(id: Int) = fitnessDao.getById(id)
}