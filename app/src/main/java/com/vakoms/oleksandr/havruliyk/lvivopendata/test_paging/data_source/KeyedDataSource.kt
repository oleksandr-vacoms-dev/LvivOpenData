package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import java.util.concurrent.Executor

abstract class KeyedDataSource<T, K>(var openDataApi: OpenDataApi, var retryExecutor: Executor) :
    PageKeyedDataSource<Int, T>() {

    var retry: (() -> Any)? = null
    val network = MutableLiveData<NetworkState>()
    val initial = MutableLiveData<NetworkState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {

        val currentPage = 1
        val nextPage = currentPage + 1

        makeLoadInitialRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadInitialRequest(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>,
        currentPage: Int,
        nextPage: Int
    ) {

        searchUsersSync(
            page = currentPage,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                retry = null
                postInitialState(NetworkState.LOADED)
                callback.onResult(getData(responseBody), null, nextPage)
            },
            onError = { errorMessage ->
                retry = { loadInitial(params, callback) }
                postInitialState(NetworkState.error(errorMessage))
            })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, T>
    ) {

        val currentPage = params.key
        val nextPage = currentPage + 1

        makeLoadAfterRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadAfterRequest(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, T>,
        currentPage: Int,
        nextPage: Int
    ) {

        searchUsersAsync(
            page = currentPage,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                retry = null
                callback.onResult(getData(responseBody), nextPage)
                postAfterState(NetworkState.LOADED)
            },
            onError = { errorMessage ->
                retry = { loadAfter(params, callback) }
                postAfterState(NetworkState.error(errorMessage))
            })
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retry ->
            retryExecutor.execute { retry() }
        }
    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state)
        initial.postValue(state)
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state)
    }

    abstract fun searchUsersSync(
        page: Int, perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (K?) -> Unit,
        onError: (String) -> Unit
    )

    abstract fun searchUsersAsync(
        page: Int, perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (K?) -> Unit,
        onError: (String) -> Unit
    )

    abstract fun getData(response: K?): List<T>
}