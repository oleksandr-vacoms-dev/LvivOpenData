package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(database: FitnessRoomDatabase) :
    LocalDataStorage<FitnessRecord> {

    private var fitnessDao = database.fitnessDao()

    override fun getAll(callback: DataBoundaryCallback<FitnessRecord>) =
        fitnessDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    override fun deleteAll() {
        fitnessDao.deleteAll()
    }

    override fun getById(id: Int) = fitnessDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<FitnessRecord>, name: String) =
        fitnessDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}