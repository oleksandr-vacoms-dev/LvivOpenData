package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData

interface DataStorage<T> {

    fun getAllData(): LiveData<List<T>>?

    fun saveData(data: List<T>)

    fun deleteAllData()
}