package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.pharmacy.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyRecord


@Dao
interface PharmacyDao {
    @Query("SELECT * FROM pharmacy")
    fun getAll(): LiveData<List<PharmacyRecord>>

    @Query("DELETE FROM pharmacy")
    fun deleteAll()

    @Insert
    fun insert(pharmacyDataList: List<PharmacyRecord>)
}