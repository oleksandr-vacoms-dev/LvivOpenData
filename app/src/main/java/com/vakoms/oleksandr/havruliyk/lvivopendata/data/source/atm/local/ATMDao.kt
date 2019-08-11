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

    @Query("SELECT * FROM atm WHERE _id=:id")
    fun getById(id: Int): LiveData<ATMRecord>

    @Query("SELECT * FROM atm WHERE bankLabel LIKE :name ORDER BY _id")
    fun getByName(name: String): DataSource.Factory<Int, ATMRecord>

    @Query("SELECT * FROM atm ORDER BY _id")
    fun getAll(): DataSource.Factory<Int, ATMRecord>
}