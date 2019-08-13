package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(database: BarberRoomDatabase) {

    private var barberDao = database.barberDao()

    fun getAll(callback: PagedList.BoundaryCallback<BarberRecord>) =
        barberDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    fun deleteAll() {
        barberDao.deleteAll()
    }

    fun getById(id: Int) = barberDao.getById(id)

    fun getByName(callback: PagedList.BoundaryCallback<BarberRecord>, name: String) =
        barberDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}