package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(database: BarberRoomDatabase) :
    LocalDataStorage<BarberRecord> {

    private var barberDao = database.barberDao()

    override fun getAll(callback: DataBoundaryCallback<BarberRecord>) =
        barberDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

     fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    override fun deleteAll() {
        barberDao.deleteAll()
    }

    override fun getById(id: Int) = barberDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<BarberRecord>, name: String) =
        barberDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}