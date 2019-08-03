package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord

@Database(entities = [FitnessRecord::class], version = 1, exportSchema = false)
abstract class FitnessRoomDatabase : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao
}