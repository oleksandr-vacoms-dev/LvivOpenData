package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
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
}