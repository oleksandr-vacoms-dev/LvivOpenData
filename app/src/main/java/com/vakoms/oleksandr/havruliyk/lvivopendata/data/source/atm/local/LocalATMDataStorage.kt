package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(database: ATMRoomDatabase) :
    LocalDataStorage<ATMRecord> {

    private var atmDao = database.atmDao()

    override fun getAll(callback: DataBoundaryCallback<ATMRecord>) =
        atmDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    override fun saveAll(data: List<ATMRecord>) {
        atmDao.insert(data)
    }

    override fun deleteAll() {
        atmDao.deleteAll()
    }

    override fun getById(id: Int) = atmDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<ATMRecord>, name: String) =
        atmDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}