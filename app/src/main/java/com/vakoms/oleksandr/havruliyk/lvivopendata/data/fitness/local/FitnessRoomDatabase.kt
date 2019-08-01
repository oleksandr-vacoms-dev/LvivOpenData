package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

@Database(entities = [FitnessCentersRecord::class], version = 1, exportSchema = false)
abstract class FitnessRoomDatabase : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao
}