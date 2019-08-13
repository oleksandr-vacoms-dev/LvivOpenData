package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord

@Dao
interface BarberDao {
    @Query("DELETE FROM barber")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<BarberRecord>)

    @Query("SELECT * FROM barber WHERE _id>:offset AND _id<=:lastId ORDER BY _id")
    fun get(offset: Int, lastId: Int): LiveData<List<BarberRecord>>

    @Query("SELECT * FROM barber WHERE name LIKE :name ORDER BY _id")
    fun getByName(name: String): LiveData<List<BarberRecord>>

    @Query("SELECT * FROM barber WHERE _id=:id")
    fun getById(id: Int): LiveData<BarberRecord>

    @Query("SELECT * FROM barber WHERE name LIKE :name ORDER BY _id")
    fun getDataSourceFactoryByName(name: String): DataSource.Factory<Int, BarberRecord>

    @Query("SELECT * FROM barber ORDER BY _id")
    fun getDataSourceFactory(): DataSource.Factory<Int, BarberRecord>
}