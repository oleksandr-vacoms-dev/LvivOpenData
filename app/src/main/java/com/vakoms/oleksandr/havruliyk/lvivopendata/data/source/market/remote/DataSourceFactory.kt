package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import retrofit2.Call
import java.util.concurrent.Executor

abstract class DataSourceFactory<T, K>(
    private val request: (page: Int) -> Call<K>,
    private val onResponse: (response: K?) -> List<T>,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, T>() {

    val source = MutableLiveData<KeyedDataSource<T, K>>()

    override fun create(): DataSource<Int, T> {
        val source = object : KeyedDataSource<T, K>(retryExecutor, request, onResponse) {}

        this.source.postValue(source)
        return source
    }

    fun getListing() = Listing(
        pagedList = toLiveData(
            config = pagedListConfig()
        ),
        networkState = Transformations.switchMap(source) { it.network },
        retry = { source.value?.retryAllFailed() },
        refresh = { source.value?.invalidate() },
        refreshState = Transformations.switchMap(source) { it.initial })
}