package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord


@Dao
interface ATMDao {
    @Query("SELECT * FROM atm")
    fun getAll(): LiveData<List<ATMRecord>>

    @Query("DELETE FROM atm")
    fun deleteAll()

    @Insert
    fun insert(data: List<ATMRecord>)
}