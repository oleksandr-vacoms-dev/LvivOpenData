package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromBarberRecord
import javax.inject.Inject

class BarberViewModel @Inject constructor(
    private var repository: BarberRepository,
    private var mapManager: MapManager
) : ViewModel() {

    private lateinit var listing: Listing<BarberRecord>

    var networkState: LiveData<NetworkState> = MutableLiveData()
    var refreshState: LiveData<NetworkState> = MutableLiveData()
    var pagedList: LiveData<PagedList<BarberRecord>> = MutableLiveData()

    private val searchString = MutableLiveData<String>()
    var searchNetworkState: LiveData<NetworkState> = MutableLiveData()
    var searchRefreshState: LiveData<NetworkState> = MutableLiveData()

    var searchPagedList: LiveData<PagedList<BarberRecord>> = Transformations.switchMap(searchString) { name ->
        listing = repository.getDataByName(name)
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

    fun addRecordsToMap(record: List<BarberRecord>) {
        mapManager.addRecords(getAddressRecordFromBarberRecord(record))
    }

    fun getAllData() {
        listing = repository.getData()
        networkState = listing.networkState
        refreshState = listing.refreshState
        pagedList = listing.pagedList
    }
}