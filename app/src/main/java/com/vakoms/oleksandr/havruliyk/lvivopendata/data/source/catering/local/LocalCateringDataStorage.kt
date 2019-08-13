package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(
    database: CateringRoomDatabase
) : DataStorage<CateringRecord> {

    private var cateringDao = database.cateringDao()

    override fun getListing() = Listing(
        factory = cateringDao.getDataSourceFactory()
    )

    override fun getListingByName(name: String) = Listing(
        factory = cateringDao.getDataSourceFactoryByName("%$name%")
    )

    override fun get(offset: Int, amount: Int) =
        cateringDao.get(offset, amount)

    override fun getByName(name: String, offset: Int, amount: Int) =
        cateringDao.getByName("%$name%")

    override fun save(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    override fun deleteAll() {
        cateringDao.deleteAll()
    }

    override fun getById(id: Int) = cateringDao.getById(id)
}