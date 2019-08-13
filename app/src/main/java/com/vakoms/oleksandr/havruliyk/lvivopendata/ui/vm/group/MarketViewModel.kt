package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromMarketRecord
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private var repository: MarketRepository,
    private var mapManager: MapManager
) : ViewModel() {

    private lateinit var listing: Listing<MarketRecord>

    var networkState: LiveData<NetworkState> = MutableLiveData()
    var refreshState: LiveData<NetworkState> = MutableLiveData()
    var pagedList: LiveData<PagedList<MarketRecord>> = MutableLiveData()

    private val searchString = MutableLiveData<String>()
    var searchNetworkState: LiveData<NetworkState> = MutableLiveData()
    var searchRefreshState: LiveData<NetworkState> = MutableLiveData()

    var searchPagedList: LiveData<PagedList<MarketRecord>> = Transformations.switchMap(searchString) { name ->
        listing = repository.getListingByName(name)
        searchNetworkState = listing.networkState
        searchRefreshState = listing.refreshState
        listing.pagedList
    }

    fun refresh() {
        listing.refresh.invoke()
    }

    fun retry() {
        listing.retry.invoke()
    }

    fun getDataByQuery(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<MarketRecord>) {
        mapManager.addRecords(getAddressRecordFromMarketRecord(record))
    }

    fun getAllData() {
        listing = repository.getListing()
        networkState = listing.networkState
        refreshState = listing.refreshState
        pagedList = listing.pagedList
    }
}