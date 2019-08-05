package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage

class LocalMarketDataStorage(context: Context) : DataStorage<MarketRecord> {

    private var marketDao: MarketDao

    init {
        val roomDB: MarketRoomDatabase = Room.databaseBuilder(
            context,
            MarketRoomDatabase::class.java, "market_db"
        )
            .allowMainThreadQueries()
            .build()

        marketDao = roomDB.marketDao()
    }

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
}