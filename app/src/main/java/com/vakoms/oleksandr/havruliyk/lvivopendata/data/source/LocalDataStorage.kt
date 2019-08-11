package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource

interface LocalDataStorage<T> {

    fun getAll(): DataSource.Factory<Int, T>

    fun getByName(name: String): DataSource.Factory<Int, T>

    fun saveAll(data: List<T>)

    fun deleteAll()

    fun getById(id: Int): LiveData<T>
}