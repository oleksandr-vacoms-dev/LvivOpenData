package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord

@Database(entities = [MarketsRecord::class], version = 1, exportSchema = false)
abstract class MarketRoomDatabase : RoomDatabase() {
    abstract fun marketDao(): MarketDao
}