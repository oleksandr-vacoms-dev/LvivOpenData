package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.LocalDataStorage

abstract class Repository<T>(
    private val localDataStorage: LocalDataStorage<T>,
    private val openDataApi: OpenDataApi
) : DataStorage<T> {

    override fun getById(id: Int): LiveData<T>? = localDataStorage.getById(id)

    override fun getByName(name: String): LiveData<List<T>>? = localDataStorage.getByName(name)
}