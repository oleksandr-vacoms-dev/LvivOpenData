package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord

@Dao
interface BarberDao {
    @Query("SELECT * FROM barber")
    fun getAll(): LiveData<List<BarberRecord>>

    @Query("DELETE FROM barber")
    fun deleteAll()

    @Insert
    fun insert(barberDataList: List<BarberRecord>)
}