package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    var localDataStorage: LocalFitnessDataStorage,
    var openDataApi: OpenDataApi
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

    override fun getById(id: Int): LiveData<FitnessRecord> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(data: List<FitnessRecord>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}