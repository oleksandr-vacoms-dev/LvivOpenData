package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData

interface DataStorage<T> {

    fun getAll(): LiveData<List<T>>?

    fun saveAll(data: List<T>)

    fun deleteAll()

    fun getById(id: Int): LiveData<T>?

    fun getByName(name: String): LiveData<List<T>>?
}