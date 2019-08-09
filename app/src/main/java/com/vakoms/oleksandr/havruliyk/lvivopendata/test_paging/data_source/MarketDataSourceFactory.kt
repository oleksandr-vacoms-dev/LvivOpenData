package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import java.util.concurrent.Executor

class MarketDataSourceFactory(
    private val openDataApi: OpenDataApi,
    private val retryExecutor: Executor,
    private val context: Context
) : DataSource.Factory<Int, MarketRecord>() {

    val source = MutableLiveData<MarketKeyedDataSource>()

    override fun create(): DataSource<Int, MarketRecord> {
        val source =
            MarketKeyedDataSource(
                openDataApi,
                retryExecutor,
                context
            )
        this.source.postValue(source)
        return source
    }
}