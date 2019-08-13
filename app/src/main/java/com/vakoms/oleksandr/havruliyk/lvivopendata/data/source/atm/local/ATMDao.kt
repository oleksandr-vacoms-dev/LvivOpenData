package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord

@Dao
interface ATMDao {
    @Query("DELETE FROM atm")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<ATMRecord>)

    @Query("SELECT * FROM atm WHERE _id>:offset AND _id<=:lastId ORDER BY _id")
    fun get(offset: Int, lastId: Int): LiveData<List<ATMRecord>>

    @Query("SELECT * FROM atm WHERE bankLabel LIKE :name ORDER BY _id")
    fun getByName(name: String): LiveData<List<ATMRecord>>

    @Query("SELECT * FROM atm WHERE _id=:id")
    fun getById(id: Int): LiveData<ATMRecord>

    @Query("SELECT * FROM atm WHERE bankLabel LIKE :name ORDER BY _id")
    fun getDataSourceFactoryByName(name: String): DataSource.Factory<Int, ATMRecord>

    @Query("SELECT * FROM atm ORDER BY _id")
    fun getDataSourceFactory(): DataSource.Factory<Int, ATMRecord>
}