package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState

abstract class Repository<T> : DataStorage<T> {

    protected fun getListing(
        boundaryCallback: DataBoundaryCallback<T>,
        livePagedList: LiveData<PagedList<T>>,
        refreshStateLiveData: () -> LiveData<NetworkState>
    ): Listing<T> {

        // Using a mutable live data to trigger refresh requests which eventually calls
        // refresh method and gets a new live data. Each refresh request by the user becomes a newly
        // dispatched data in refreshTrigger
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState: LiveData<NetworkState> = Transformations.switchMap(refreshTrigger) {
            refreshStateLiveData()
        }

        return Listing(
            pagedList = livePagedList,
            networkState = boundaryCallback.networkState,
            retry = {
                boundaryCallback.helper.retryAllFailed()
            },
            refresh = {
                refreshTrigger.value = null
            },
            refreshState = refreshState
        )
    }
}