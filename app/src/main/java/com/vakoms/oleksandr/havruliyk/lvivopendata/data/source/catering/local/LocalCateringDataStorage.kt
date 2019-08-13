package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(database: CateringRoomDatabase) {

    private var cateringDao = database.cateringDao()

    fun getAll(callback: PagedList.BoundaryCallback<CateringRecord>) =
        cateringDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun saveAll(data: List<CateringRecord>) {
        cateringDao.insert(data)
    }

    fun deleteAll() {
        cateringDao.deleteAll()
    }

    fun getById(id: Int) = cateringDao.getById(id)

    fun getByName(callback: PagedList.BoundaryCallback<CateringRecord>, name: String) =
        cateringDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}