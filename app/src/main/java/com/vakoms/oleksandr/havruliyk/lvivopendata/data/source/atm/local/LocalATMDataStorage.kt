package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalATMDataStorage @Inject constructor(
    database: ATMRoomDatabase
) : DataStorage<ATMRecord> {

    override fun getListing(): Listing<ATMRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListingByName(name: String): Listing<ATMRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(offset: Int, amount: Int): LiveData<List<ATMRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<ATMRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: Int): LiveData<ATMRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(data: List<ATMRecord>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var atmDao = database.atmDao()

    fun getAll(callback: PagedList.BoundaryCallback<ATMRecord>) =
        atmDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun getByName(callback: PagedList.BoundaryCallback<ATMRecord>, name: String) =
        atmDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}