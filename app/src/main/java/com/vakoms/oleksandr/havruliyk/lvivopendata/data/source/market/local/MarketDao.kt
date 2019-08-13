package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord

@Dao
interface MarketDao {

    @Query("DELETE FROM market")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun insert(data: List<MarketRecord>)

    @Query("SELECT * FROM market WHERE id>:offset AND id<=:lastId ORDER BY id")
    fun get(offset: Int, lastId: Int): LiveData<List<MarketRecord>>

    @Query("SELECT * FROM market WHERE name LIKE :name ORDER BY id")
    fun getByName(name: String): LiveData<List<MarketRecord>>

    @Query("SELECT * FROM market WHERE id=:id")
    fun getById(id: Int): LiveData<MarketRecord>

    @Query("SELECT * FROM market WHERE name LIKE :name ORDER BY id")
    fun getDataSourceFactoryByName(name: String): DataSource.Factory<Int, MarketRecord>

    @Query("SELECT * FROM market ORDER BY id")
    fun getDataSourceFactory(): DataSource.Factory<Int, MarketRecord>
}