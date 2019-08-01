package com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersRecord

@Dao
interface FitnessDao {
    @Query("SELECT * FROM fitness")
    fun getAll(): LiveData<List<FitnessCentersRecord>>

    @Query("DELETE FROM fitness")
    fun deleteAll()

    @Insert
    fun insert(weathers: List<FitnessCentersRecord>)
}