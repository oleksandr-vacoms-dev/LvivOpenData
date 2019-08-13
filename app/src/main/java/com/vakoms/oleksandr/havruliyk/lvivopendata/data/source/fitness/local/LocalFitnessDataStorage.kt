package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalFitnessDataStorage @Inject constructor(
    database: FitnessRoomDatabase
) : DataStorage<FitnessRecord> {
    override fun getListing(): Listing<FitnessRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListingByName(name: String): Listing<FitnessRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(data: List<FitnessRecord>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: Int): LiveData<FitnessRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var fitnessDao = database.fitnessDao()

    fun getAll(callback: PagedList.BoundaryCallback<FitnessRecord>) =
        fitnessDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    fun saveAll(data: List<FitnessRecord>) {
        fitnessDao.insert(data)
    }

    fun getByName(callback: PagedList.BoundaryCallback<FitnessRecord>, name: String) =
        fitnessDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}