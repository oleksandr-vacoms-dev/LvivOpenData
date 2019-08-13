package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing

interface DataStorage<T> {

    fun getListing(): Listing<T>

    fun getListingByName(name: String): Listing<T>

    fun get(offset: Int, amount: Int): LiveData<List<T>>

    fun getByName(name: String, offset: Int, amount: Int): LiveData<List<T>>

    fun getById(id: Int): LiveData<T>

    fun save(data: List<T>)

    fun deleteAll()
}