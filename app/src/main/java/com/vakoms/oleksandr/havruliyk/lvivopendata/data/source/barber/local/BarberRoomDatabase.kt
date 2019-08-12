package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord

@Database(entities = [BarberRecord::class], version = 1, exportSchema = false)
abstract class BarberRoomDatabase : RoomDatabase() {
    abstract fun barberDao(): BarberDao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, BarberRoomDatabase::class.java, "barber_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}