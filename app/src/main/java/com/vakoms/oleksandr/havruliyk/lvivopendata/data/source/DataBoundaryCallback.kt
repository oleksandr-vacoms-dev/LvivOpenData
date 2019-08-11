package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source

import androidx.paging.PagedList
import androidx.paging.PagingRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.createStatusLiveData
import java.util.concurrent.Executor

abstract class DataBoundaryCallback<T>(
    ioExecutor: Executor
) : PagedList.BoundaryCallback<T>() {

    val helper = PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    override fun onItemAtFrontLoaded(itemAtFront: T) {

    }
}