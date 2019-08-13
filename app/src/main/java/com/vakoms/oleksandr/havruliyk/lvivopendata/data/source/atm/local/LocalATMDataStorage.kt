package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(database: ATMRoomDatabase) {

    private var atmDao = database.atmDao()

    fun getAll(callback: PagedList.BoundaryCallback<ATMRecord>) =
        atmDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun saveAll(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    fun deleteAll() {
        atmDao.deleteAll()
    }

    fun getById(id: Int) = atmDao.getById(id)

    fun getByName(callback: PagedList.BoundaryCallback<ATMRecord>, name: String) =
        atmDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}