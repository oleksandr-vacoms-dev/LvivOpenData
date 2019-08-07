package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord

@Database(entities = [ATMRecord::class], version = 1, exportSchema = false)
abstract class ATMRoomDatabase : RoomDatabase() {
    abstract fun atmDao(): ATMDao
}