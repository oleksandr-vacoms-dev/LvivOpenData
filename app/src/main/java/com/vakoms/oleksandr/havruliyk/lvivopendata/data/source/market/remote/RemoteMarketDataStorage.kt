package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.toLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.PAGE_SIZE
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.sqlMarkets
import java.util.concurrent.Executor
import javax.inject.Inject


class RemoteMarketDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi,
    private val networkExecutor: Executor

) {
    fun deleteAll() {
    }

    fun getById(id: Int) {
    }

    @MainThread
    fun getAll(): Listing<MarketRecord> =
        object : DataSourceFactory<MarketRecord, MarketsResponse>(
            request = { page ->
                openDataApi.getMarkets(sqlMarkets(page * PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            },
            retryExecutor = networkExecutor
        ){}.getListing()

//    fun getAll() = Listing(
//        pagedList = marketDao.getAll().toLiveData(
//            config = pagedListConfig()
//        ),
//        refreshState = refreshState,
//        networkState = networkState,
//        refresh = {},
//        retry = {}
//    )
}