package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord

@Database(entities = [ATMRecord::class], version = 1, exportSchema = false)
abstract class ATMRoomDatabase : RoomDatabase() {
    abstract fun atmDao(): ATMDao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, ATMRoomDatabase::class.java, "atm_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}