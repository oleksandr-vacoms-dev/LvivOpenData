package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(database: CateringRoomDatabase) :
    LocalDataStorage<CateringRecord> {

    private var cateringDao = database.cateringDao()

    override fun getAll(callback: DataBoundaryCallback<CateringRecord>) =
        cateringDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    override fun deleteAll() {
        cateringDao.deleteAll()
    }

    override fun getById(id: Int) = cateringDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<CateringRecord>, name: String) =
        cateringDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}