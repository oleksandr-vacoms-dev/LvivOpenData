package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.MarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord

class LocalMarketDataStorage(context: Context) : MarketDataStorage {

    companion object {
        const val TAG = "LocalMarketDataStorage"

        private var INSTANCE: LocalMarketDataStorage? = null

        fun getInstance(context: Context): LocalMarketDataStorage? {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalMarketDataStorage(context)
            }
            return INSTANCE
        }
    }

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

    override fun getMarketData(): LiveData<List<MarketsRecord>>? {
        return marketDao.getAll()
    }

    override fun saveMarketData(data: List<MarketsRecord>) {
        marketDao.insert(data)
    }

    override fun deleteAllData() {
        marketDao.deleteAll()
    }

    override fun destroyInstance() {
        INSTANCE = null
    }

}