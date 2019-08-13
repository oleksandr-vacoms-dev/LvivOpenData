package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.ApiRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import org.jetbrains.anko.doAsync
import retrofit2.Call
import java.util.concurrent.Executor

abstract class KeyedDataSource<T, K>(
    private val request: (page: Int) -> Call<K>,
    private val onResponse: (response: K?) -> List<T>
) :
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

        val currentPage = 0
        val nextPage = currentPage + 1

        makeLoadInitialRequest(params, callback, currentPage, nextPage)
    }

    private fun makeLoadInitialRequest(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>,
        currentPage: Int,
        nextPage: Int
    ) {

        getDataSync(
            page = currentPage,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                retry = null
                postInitialState(NetworkState.LOADED)
                callback.onResult(onResponse(responseBody), null, nextPage)
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

        getDataAsync(
            page = currentPage,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                retry = null
                callback.onResult(onResponse(responseBody), nextPage)
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
            doAsync { retry() }
        }
    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state)
        initial.postValue(NetworkState.LOADED)
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state)
    }

    private fun getDataSync(
        page: Int,
        onPrepared: () -> Unit,
        onSuccess: (K?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = request(page)
        onPrepared()
        ApiRequestHelper.syncRequest(request, onSuccess, onError)
    }

    private fun getDataAsync(
        page: Int,
        onPrepared: () -> Unit,
        onSuccess: (K?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = request(page)
        onPrepared()
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }
}