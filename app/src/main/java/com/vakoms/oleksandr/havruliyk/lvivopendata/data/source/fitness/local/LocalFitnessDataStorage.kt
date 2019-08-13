package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(database: FitnessRoomDatabase) {

    private var fitnessDao = database.fitnessDao()

     fun getAll(callback: PagedList.BoundaryCallback<FitnessRecord>) =
        fitnessDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

     fun deleteAll() {
        fitnessDao.deleteAll()
    }

     fun getById(id: Int) = fitnessDao.getById(id)

     fun getByName(callback: PagedList.BoundaryCallback<FitnessRecord>, name: String) =
        fitnessDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}