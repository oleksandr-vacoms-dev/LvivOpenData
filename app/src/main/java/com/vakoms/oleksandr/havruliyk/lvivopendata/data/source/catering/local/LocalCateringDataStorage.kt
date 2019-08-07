package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(database: CateringRoomDatabase) : DataStorage<CateringRecord> {

    private var cateringDao: CateringDao = database.cateringDao()

    override fun getAll(): LiveData<List<CateringRecord>> {
        return cateringDao.getAll()
    }

    override fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    override fun deleteAll() {
        cateringDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<CateringRecord> = cateringDao.getById(id)
}