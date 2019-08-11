package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord

@Dao
interface CateringDao {
    @Query("DELETE FROM catering")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<CateringRecord>)

    @Query("SELECT * FROM catering WHERE _id=:id")
    fun getById(id: Int): LiveData<CateringRecord>

    @Query("SELECT * FROM catering WHERE name LIKE :name ORDER BY _id")
    fun getByName(name: String): DataSource.Factory<Int, CateringRecord>

    @Query("SELECT * FROM catering ORDER BY _id")
    fun getAll(): DataSource.Factory<Int, CateringRecord>
}