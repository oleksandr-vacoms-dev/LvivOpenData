package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing

interface DataStorage<T> {

    fun getData(): Listing<T>

    fun getById(id: Int): LiveData<T>?

    fun getByName(name: String): LiveData<List<T>>?
}