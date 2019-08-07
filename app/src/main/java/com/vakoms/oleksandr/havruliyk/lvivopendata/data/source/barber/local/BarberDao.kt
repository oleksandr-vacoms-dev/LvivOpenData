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
    fun insert(data: List<BarberRecord>)

    @Query("SELECT * FROM barber WHERE _id=:id")
    fun getById(id: Int): LiveData<BarberRecord>

    @Query("SELECT * FROM barber WHERE name LIKE :name")
    fun getByName(name: String): LiveData<List<BarberRecord>>
}