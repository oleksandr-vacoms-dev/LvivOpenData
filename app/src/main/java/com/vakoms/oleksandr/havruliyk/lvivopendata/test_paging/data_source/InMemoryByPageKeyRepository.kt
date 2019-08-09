package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.MarketRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.pagedListConfig
import java.util.concurrent.Executor

class InMemoryByPageKeyRepository(
    private val openDataApi: OpenDataApi,
    private val networkExecutor: Executor,
    private val context: Context
) {

    val local = Room
        .databaseBuilder(context, MarketRoomDatabase::class.java, "market_db")
        .fallbackToDestructiveMigration()
        .build()

    @MainThread
    fun getMarkets(): Listing<MarketRecord> = with(
        MarketDataSourceFactory(
            openDataApi,
            networkExecutor,
            context
        )
    ) {
        Listing(
            pagedList = LivePagedListBuilder(this, pagedListConfig())
                .setFetchExecutor(networkExecutor)
                .build(),
            networkState = switchMap(source) { it.network },
            retry = { source.value?.retryAllFailed() },
            refresh = { source.value?.invalidate() },
            refreshState = switchMap(source) { it.initial })
    }
}