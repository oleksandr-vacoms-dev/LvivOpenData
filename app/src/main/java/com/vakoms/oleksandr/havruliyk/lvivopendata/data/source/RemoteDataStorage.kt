package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData

abstract class RemoteDataStorage<T> : DataStorage<T> {
    override fun getById(id: Int): LiveData<T>? = null
}
