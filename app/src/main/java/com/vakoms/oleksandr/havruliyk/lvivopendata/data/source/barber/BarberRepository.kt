package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.LocalBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.RemoteBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.PagingCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class BarberRepository @Inject constructor(
    private val localDataStorage: LocalBarberDataStorage,
    private val remoteDataSource: RemoteBarberDataStorage
) : DataStorage<BarberRecord> {

    override fun getListing(): Listing<BarberRecord> {
        val callback = object : PagingCallback<BarberRecord>(
            onZeroItems = {
                remoteDataSource.get(FIRST_ITEM, PAGE_SIZE)
            },
            onItemAtEnd = { itemAtEnd ->
                remoteDataSource.get(itemAtEnd._id, PAGE_SIZE)
            },
            onNewItemsLoaded = { items ->
                save(items)
            }
        ) {}

        return callback.getListing(localDataStorage.getListing().factory)
    }

    override fun getListingByName(name: String): Listing<BarberRecord> {
        val callback = object : PagingCallback<BarberRecord>(
            onZeroItems = {
                remoteDataSource.getByName(name, FIRST_ITEM, PAGE_SIZE)
            },
            onItemAtEnd = { itemAtEnd ->
                remoteDataSource.getByName(name, itemAtEnd._id, PAGE_SIZE)
            },
            onNewItemsLoaded = { items ->
                save(items)
            }
        ) {}

        return callback.getListing(localDataStorage.getListingByName(name).factory)
    }

    override fun get(offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        remoteDataSource.get(offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.get(offset, amount)
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        remoteDataSource.getByName(name, offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.getByName(name, offset, amount)
    }

    override fun getById(id: Int): LiveData<BarberRecord> {
        remoteDataSource.getById(id).observeForever { data ->
            if (data != null) {
                save(listOf(data))
            }
        }

        return localDataStorage.getById(id)
    }

    override fun save(data: List<BarberRecord>) {
        doAsync { localDataStorage.save(data) }
    }

    override fun deleteAll() {
        doAsync { localDataStorage.deleteAll() }
    }
}