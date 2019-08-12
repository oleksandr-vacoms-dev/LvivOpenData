package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing

interface DataStorage<T> {

    fun getAll(): Listing<T>

    fun getByName(name: String): Listing<T>

    fun saveAll(newData: List<T>)
}