package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.MarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

class LocalMarketDataStorage(context: Context) : MarketDataStorage {

    private var marketDao: MarketDao

    init {
        val roomDB: MarketRoomDatabase = Room.databaseBuilder(
            context,
            MarketRoomDatabase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

        marketDao = roomDB.marketDao()
    }

    override fun getMarketData(): LiveData<List<MarketRecord>>? {
        return marketDao.getAll()
    }

    override fun saveMarketData(data: List<MarketRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAllData() {
        marketDao.deleteAll()
    }
}