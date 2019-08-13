package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalBarberDataStorage @Inject constructor(
    database: BarberRoomDatabase
) : DataStorage<BarberRecord> {

    override fun getListing(): Listing<BarberRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListingByName(name: String): Listing<BarberRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: Int): LiveData<BarberRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(data: List<BarberRecord>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var barberDao = database.barberDao()

    fun getAll(callback: PagedList.BoundaryCallback<BarberRecord>) =
        barberDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun saveAll(data: List<BarberRecord>) {
        barberDao.insert(data)
    }

    fun getByName(callback: PagedList.BoundaryCallback<BarberRecord>, name: String) =
        barberDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}