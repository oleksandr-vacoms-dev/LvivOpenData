package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface LocalDataStorage<T> {//: DataStorage<T>{

    fun getAll(callback: DataBoundaryCallback<T>): LiveData<PagedList<T>>

    fun getByName(callback: DataBoundaryCallback<T>, name: String): LiveData<PagedList<T>>

    fun deleteAll()

    fun getById(id: Int): LiveData<T>
}