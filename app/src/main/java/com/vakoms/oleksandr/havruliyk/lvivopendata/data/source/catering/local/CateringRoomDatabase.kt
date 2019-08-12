package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord

@Database(entities = [CateringRecord::class], version = 1, exportSchema = false)
abstract class CateringRoomDatabase : RoomDatabase() {
    abstract fun cateringDao(): CateringDao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, CateringRoomDatabase::class.java, "catering_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}