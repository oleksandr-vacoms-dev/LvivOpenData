package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import org.jetbrains.anko.doAsync

abstract class PagingCallback<T>(
    private val onItemAtEnd: (itemAtEnd: T) -> LiveData<List<T>>,
    private val onZeroItems: () -> LiveData<List<T>>,
    private val onNewItemsLoaded: (List<T>) -> Unit
) : PagedList.BoundaryCallback<T>() {

    val networkState = MutableLiveData<NetworkState>()
    val refreshState = MutableLiveData<NetworkState>()

    private var lastItemAtEnd: T? = null

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        lastItemAtEnd = itemAtEnd

        networkState.postValue(NetworkState.LOADING)

        onItemAtEnd(itemAtEnd).observeForever { records ->
            handleNewRecords(records)
        }
    }

    override fun onZeroItemsLoaded() {
        networkState.postValue(NetworkState.LOADING)

        onZeroItems().observeForever { records ->
            handleNewRecords(records)
        }
    }

    fun retry() {
        if (lastItemAtEnd != null) {
            onItemAtEndLoaded(lastItemAtEnd!!)
        }
    }

    fun refresh() {
        refreshState.postValue(NetworkState.LOADING)
        onZeroItemsLoaded()
    }

    private fun handleNewRecords(records: List<T>?) {
        if (records == null) {
            networkState.postValue(NetworkState.error("failed"))
            refreshState.postValue(NetworkState.error("failed"))
        } else {
            doAsync {
                onNewItemsLoaded(records)
                networkState.postValue(NetworkState.LOADED)
                refreshState.postValue(NetworkState.LOADED)
            }
        }
    }

    fun getListing(factory: DataSource.Factory<Int, T>): Listing<T> {
        return Listing(
            factory = factory,
            callback = this,
            refreshState = refreshState,
            networkState = networkState,
            retry = { retry() },
            refresh = { refresh() }
        )
    }
}