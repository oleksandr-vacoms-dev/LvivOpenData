package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData

abstract class RemoteDataStorage<T> : DataStorage<T> {
    override fun saveAll(data: List<T>) {}

    override fun deleteAll() {}

    override fun getById(id: Int): LiveData<T>? = null
}
