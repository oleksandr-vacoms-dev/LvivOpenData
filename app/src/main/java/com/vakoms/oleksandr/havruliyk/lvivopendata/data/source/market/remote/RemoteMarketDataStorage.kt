package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed.KeyedDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import javax.inject.Inject

class RemoteMarketDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi
) : DataStorage<MarketRecord> {

    override fun getListing() =
        object : KeyedDataSourceFactory<MarketRecord, MarketsResponse>(
            request = { page ->
                openDataApi.getMarkets(sqlMarkets(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun getListingByName(name: String) =
        object : KeyedDataSourceFactory<MarketRecord, MarketsResponse>(
            request = { page ->
                openDataApi.getMarkets(sqlMarkets(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun get(offset: Int, amount: Int): LiveData<List<MarketRecord>> {
        val data = MutableLiveData<List<MarketRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getMarkets(sqlMarkets(offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<MarketRecord>> {
        val data = MutableLiveData<List<MarketRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getMarkets(sqlMarketsByName(name, offset)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getById(id: Int): LiveData<MarketRecord> {
        val data = MutableLiveData<MarketRecord>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getMarkets(sqlMarketById(id)),
            onSuccess = { response ->
                with(response?.result?.records!!) {
                    if (this.isNotEmpty()) {
                        data.value = this[0]
                    }
                }
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun save(data: List<MarketRecord>) {
    }

    override fun deleteAll() {
    }
}