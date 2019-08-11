package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.LocalDataStorage
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(var database: MarketRoomDatabase): LocalDataStorage<MarketRecord>{

    var marketDao: MarketDao = database.marketDao()

    override fun getAll() = marketDao.getAll()

    override fun saveAll(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int): LiveData<MarketRecord> = marketDao.getById(id)

    override fun getByName(name: String): DataSource.Factory<Int, MarketRecord> = marketDao.getByName(name)
}