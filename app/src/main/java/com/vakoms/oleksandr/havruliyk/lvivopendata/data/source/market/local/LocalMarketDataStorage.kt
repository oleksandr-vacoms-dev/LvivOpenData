package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataBoundaryCallback
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.LocalDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import javax.inject.Inject

class LocalMarketDataStorage @Inject constructor(var database: MarketRoomDatabase) :
    LocalDataStorage<MarketRecord> {

    private var marketDao = database.marketDao()

    override fun getAll(callback: DataBoundaryCallback<MarketRecord>) =
        marketDao.getAll().toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )

    override fun saveAll(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAll() {
        marketDao.deleteAll()
    }

    override fun getById(id: Int) = marketDao.getById(id)

    override fun getByName(callback: DataBoundaryCallback<MarketRecord>, name: String) =
        marketDao.getByName("%$name%").toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
}