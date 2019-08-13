package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.PagingCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.FIRST_ITEM
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class MarketRepository @Inject constructor(
    private val localDataStorage: LocalMarketDataStorage,
    private val remoteDataSource: RemoteMarketDataStorage
) : DataStorage<MarketRecord> {

    override fun getListing(): Listing<MarketRecord> {
        val callback = object : PagingCallback<MarketRecord>(
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

    override fun getListingByName(name: String): Listing<MarketRecord> {
        val callback = object : PagingCallback<MarketRecord>(
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

    override fun get(offset: Int, amount: Int): LiveData<List<MarketRecord>> {
        remoteDataSource.get(offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.get(offset, amount)
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<MarketRecord>> {
        remoteDataSource.getByName(name, offset, amount).observeForever { data ->
            if (data != null) {
                save(data)
            }
        }

        return localDataStorage.getByName(name, offset, amount)
    }

    override fun getById(id: Int): LiveData<MarketRecord> {
        remoteDataSource.getById(id).observeForever { data ->
            if (data != null) {
                save(listOf(data))
            }
        }

        return localDataStorage.getById(id)
    }

    override fun save(data: List<MarketRecord>) {
        doAsync { localDataStorage.save(data) }
    }

    override fun deleteAll() {
        doAsync { localDataStorage.deleteAll() }
    }
}