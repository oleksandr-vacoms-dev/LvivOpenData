package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market

import androidx.lifecycle.LiveData
import androidx.paging.DataSource

interface LocalDataStorage<T> {

    fun getAll(): DataSource.Factory<Int, T>

    fun saveAll(data: List<T>)

    fun deleteAll()

    fun getById(id: Int): LiveData<T>

    fun getByName(name: String): LiveData<List<T>>?
}