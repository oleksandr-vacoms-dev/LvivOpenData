package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(database: CateringRoomDatabase) :
    LocalDataStorage<CateringRecord> {

    private var cateringDao = database.cateringDao()

    override fun getAll() = cateringDao.getAll()

    override fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    override fun deleteAll() {
        cateringDao.deleteAll()
    }

    override fun getById(id: Int) = cateringDao.getById(id)

    override fun getByName(name: String) = cateringDao.getByName(name)
}