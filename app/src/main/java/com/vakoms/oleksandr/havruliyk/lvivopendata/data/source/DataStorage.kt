package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing

interface DataStorage<T> {

    fun getData(): Listing<T>

    fun getDataByName(name: String): Listing<T>

    fun saveAllData(newData: List<T>)
}