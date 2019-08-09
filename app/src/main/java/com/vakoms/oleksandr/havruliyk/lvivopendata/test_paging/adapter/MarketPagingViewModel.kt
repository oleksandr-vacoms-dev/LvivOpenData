package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.Model
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source.InMemoryByPageKeyRepository

class MarketPagingViewModel(
    repository: InMemoryByPageKeyRepository
) : ViewModel(), Model {

    private val itemResult = repository.getMarkets()
    val items = itemResult.pagedList
    val networkState = itemResult.networkState
    val refreshState = itemResult.refreshState

    override fun refresh() {
        itemResult.refresh.invoke()
    }

    override fun retry() {
        itemResult.retry.invoke()
    }

    val pagedListLiveData: LiveData<PagedList<MarketRecord>> by lazy {
        LocalMarketDataStorage(repository.local).selectPaged()
    }
}