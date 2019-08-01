package com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsRecord

@Dao
interface MarketDao {

    @Query("SELECT * FROM market")
    fun getAll(): LiveData<List<MarketsRecord>>

    @Query("DELETE FROM market")
    fun deleteAll()

    @Insert
    fun insert(weathers: List<MarketsRecord>)
}