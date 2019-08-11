package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source

import android.content.Context
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.ApiRequestHelper
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.MarketRoomDatabase
import org.jetbrains.anko.doAsync
import java.util.concurrent.Executor

class MarketKeyedDataSource(openDataApi: OpenDataApi, retryExecutor: Executor, context: Context) :
    KeyedDataSource<MarketRecord, MarketsResponse>(openDataApi, retryExecutor) {


    val local = Room
        .databaseBuilder(context, MarketRoomDatabase::class.java, "market_db")
        .fallbackToDestructiveMigration()
        .build()

    override fun searchUsersSync(
        page: Int, perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MarketsResponse?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = openDataApi.getMarkets(page * perPage)
        onPrepared()
        ApiRequestHelper.syncRequest(request, onSuccess, onError)
    }

    override fun searchUsersAsync(
        page: Int, perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MarketsResponse?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = openDataApi.getMarkets(page * perPage)
        onPrepared()
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    override fun getData(response: MarketsResponse?): List<MarketRecord> {
        val data = response?.result?.records ?: listOf()
        // TODO: added to load data to room leave or delete
        doAsync { local.marketDao().insert(data) }

        return data
    }
}