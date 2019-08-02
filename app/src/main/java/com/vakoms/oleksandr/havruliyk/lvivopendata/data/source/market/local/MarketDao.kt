package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

@Dao
interface MarketDao {

    @Query("SELECT * FROM market")
    fun getAll(): LiveData<List<MarketRecord>>

    @Query("DELETE FROM market")
    fun deleteAll()

    @Insert
    fun insert(marketDataList: List<MarketRecord>)
}