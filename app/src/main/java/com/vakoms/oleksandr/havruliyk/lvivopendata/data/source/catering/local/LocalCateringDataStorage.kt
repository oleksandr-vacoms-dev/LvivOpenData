package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(database: CateringRoomDatabase) {

    private var cateringDao: CateringDao = database.cateringDao()

    fun getAll(): LiveData<List<CateringRecord>> {
        return cateringDao.getAll()
    }

    fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    fun deleteAll() {
        cateringDao.deleteAll()
    }

    fun getById(id: Int): LiveData<CateringRecord> = cateringDao.getById(id)

    fun getByName(name: String): LiveData<List<CateringRecord>>? = cateringDao.getByName(name)
}