package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord

@Database(entities = [CateringRecord::class], version = 1, exportSchema = false)
abstract class CateringRoomDatabase : RoomDatabase() {
    abstract fun cateringDao(): CateringDao
}