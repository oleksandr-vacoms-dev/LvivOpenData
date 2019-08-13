package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalCateringDataStorage @Inject constructor(
    database: CateringRoomDatabase
) : DataStorage<CateringRecord> {

    override fun getListing(): Listing<CateringRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListingByName(name: String): Listing<CateringRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: Int): LiveData<CateringRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(data: List<CateringRecord>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var cateringDao = database.cateringDao()

    fun getAll(callback: PagedList.BoundaryCallback<CateringRecord>) =
        cateringDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun getByName(callback: PagedList.BoundaryCallback<CateringRecord>, name: String) =
        cateringDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}