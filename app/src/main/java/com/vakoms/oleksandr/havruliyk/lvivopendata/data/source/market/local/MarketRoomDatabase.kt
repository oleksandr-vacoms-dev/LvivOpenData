package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

@Database(entities = [MarketRecord::class], version = 1, exportSchema = false)
abstract class MarketRoomDatabase : RoomDatabase() {

    abstract fun marketDao(): MarketDao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, MarketRoomDatabase::class.java, "market_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}