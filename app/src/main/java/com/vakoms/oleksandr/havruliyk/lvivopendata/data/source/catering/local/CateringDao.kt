package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord

@Dao
interface CateringDao {
    @Query("SELECT * FROM catering")
    fun getAll(): LiveData<List<CateringRecord>>

    @Query("DELETE FROM catering")
    fun deleteAll()

    @Insert
    fun insert(cateringDataList: List<CateringRecord>)
}