package com.vakoms.oleksandr.havruliyk.lvivopendata.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.loadedNetwork
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig

data class Listing<T>(
    val factory: DataSource.Factory<Int, T>,
    val callback: PagedList.BoundaryCallback<T> =
        object : PagedList.BoundaryCallback<T>() {},

    // represents the network request status to show to the user
    val networkState: LiveData<NetworkState> = loadedNetwork(),
    // represents the refresh status to show to the user. Separate from network, this
    // value is importantly only when refresh is requested.
    val refreshState: LiveData<NetworkState> = loadedNetwork(),
    // refreshes the whole data and fetches it from scratch.
    val refresh: () -> Unit = {
        (refreshState as MutableLiveData).postValue(NetworkState.LOADED)
    },
    // retries any failed requests.
    val retry: () -> Unit = {}
) {
    val pagedList by lazy {
        factory.toLiveData(
            config = pagedListConfig(),
            boundaryCallback = callback
        )
    }
}