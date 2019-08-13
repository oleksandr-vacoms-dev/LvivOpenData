package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import retrofit2.Call

abstract class KeyedDataSourceFactory<T, K>(
    private val request: (page: Int) -> Call<K>,
    private val onResponse: (response: K?) -> List<T>
) : DataSource.Factory<Int, T>() {

    val source = MutableLiveData<KeyedDataSource<T, K>>()

    override fun create(): DataSource<Int, T> {
        val source = object : KeyedDataSource<T, K>(request, onResponse) {}

        this.source.postValue(source)
        return source
    }

    fun getListing() = Listing(
        factory = this,
        networkState = Transformations.switchMap(source) { it.network },
        retry = { source.value?.retryAllFailed() },
        refresh = { source.value?.invalidate() },
        refreshState = Transformations.switchMap(source) { it.initial })
}