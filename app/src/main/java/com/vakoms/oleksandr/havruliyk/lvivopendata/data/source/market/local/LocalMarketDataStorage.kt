package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(
    database: MarketRoomDatabase
) : DataStorage<MarketRecord> {

    private var marketDao = database.marketDao()

    override fun getListing() = Listing(
        factory = marketDao.getDataSourceFactory()
    )

    override fun getListingByName(name: String) = Listing(
        factory = marketDao.getDataSourceFactoryByName("%$name%")
    )

    override fun get(offset: Int, amount: Int) =
        marketDao.get(offset, amount)

    override fun getByName(name: String, offset: Int, amount: Int) =
        marketDao.getByName("%$name%")

    override fun save(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int) = marketDao.getById(id)
}