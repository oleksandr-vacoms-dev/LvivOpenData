package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.pharmacy.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyRecord


@Database(entities = [PharmacyRecord::class], version = 1, exportSchema = false)
abstract class PharmacyRoomDatabase : RoomDatabase() {
    abstract fun pharmacyDao(): PharmacyDao
}