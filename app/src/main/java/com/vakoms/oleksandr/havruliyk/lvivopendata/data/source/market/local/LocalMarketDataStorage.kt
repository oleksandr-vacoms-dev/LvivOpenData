package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(var database: MarketRoomDatabase) :
    LocalDataStorage<MarketRecord> {

    private var marketDao = database.marketDao()

    override fun getAll() = marketDao.getAll()

    override fun saveAll(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int) = marketDao.getById(id)

    override fun getByName(name: String) = marketDao.getByName(name)
}