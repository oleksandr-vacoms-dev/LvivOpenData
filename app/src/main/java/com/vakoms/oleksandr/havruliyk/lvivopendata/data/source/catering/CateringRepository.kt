package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.RemoteCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.PagingCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class CateringRepository @Inject constructor(
    var localDataStorage: LocalCateringDataStorage,
    private val remoteDataSource: RemoteCateringDataStorage
) : DataStorage<CateringRecord> {

    override fun getListing(): Listing<CateringRecord> {
        val callback = object : PagingCallback<CateringRecord>(
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

    override fun getListingByName(name: String): Listing<CateringRecord> {
        val callback = object : PagingCallback<CateringRecord>(
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

    override fun get(offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        remoteDataSource.get(offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.get(offset, amount)
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        remoteDataSource.getByName(name, offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.getByName(name, offset, amount)
    }

    override fun getById(id: Int): LiveData<CateringRecord> {
        remoteDataSource.getById(id).observeForever { data ->
            if (data != null) {
                save(listOf(data))
            }
        }

        return localDataStorage.getById(id)
    }

    override fun save(data: List<CateringRecord>) {
        doAsync { localDataStorage.save(data) }
    }

    override fun deleteAll() {
        doAsync { localDataStorage.deleteAll() }
    }
}