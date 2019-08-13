package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.PagingCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    private val localDataStorage: LocalFitnessDataStorage,
    private val remoteDataSource: RemoteFitnessDataStorage
) : DataStorage<FitnessRecord> {

    override fun getListing(): Listing<FitnessRecord> {
        val callback = object : PagingCallback<FitnessRecord>(
            onZeroItems = {
                remoteDataSource.get(FIRST_ITEM, PAGE_SIZE)
            },
            onItemAtEnd = { itemAtEnd ->
                remoteDataSource.get(itemAtEnd.id, PAGE_SIZE)
            },
            onNewItemsLoaded = { items ->
                save(items)
            }
        ) {}

        return callback.getListing(localDataStorage.getListing().factory)
    }

    override fun getListingByName(name: String): Listing<FitnessRecord> {
        val callback = object : PagingCallback<FitnessRecord>(
            onZeroItems = {
                remoteDataSource.getByName(name, FIRST_ITEM, PAGE_SIZE)
            },
            onItemAtEnd = { itemAtEnd ->
                remoteDataSource.getByName(name, itemAtEnd.id, PAGE_SIZE)
            },
            onNewItemsLoaded = { items ->
                save(items)
            }
        ) {}

        return callback.getListing(localDataStorage.getListingByName(name).factory)
    }

    override fun get(offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        remoteDataSource.get(offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.get(offset, amount)
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        remoteDataSource.getByName(name, offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.getByName(name, offset, amount)
    }

    override fun getById(id: Int): LiveData<FitnessRecord> {
        remoteDataSource.getById(id).observeForever { data ->
            if (data != null) {
                save(listOf(data))
            }
        }

        return localDataStorage.getById(id)
    }

    override fun save(data: List<FitnessRecord>) {
        doAsync { localDataStorage.save(data) }
    }

    override fun deleteAll() {
        doAsync { localDataStorage.deleteAll() }
    }
}