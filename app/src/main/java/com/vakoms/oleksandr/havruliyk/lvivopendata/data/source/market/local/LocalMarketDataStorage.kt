package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(database: MarketRoomDatabase) : DataStorage<MarketRecord> {

    var marketDao: MarketDao = database.marketDao()

    override fun getAll(): LiveData<List<MarketRecord>>? {
        return marketDao.getAll()
    }

    override fun saveAll(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<MarketRecord> = marketDao.getById(id)

    override fun getByName(name: String): LiveData<List<MarketRecord>>? = marketDao.getByName(name)

    fun selectPaged(): LiveData<PagedList<MarketRecord>> {
        val dataSourceFactory = marketDao.selectPaged()
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }
}