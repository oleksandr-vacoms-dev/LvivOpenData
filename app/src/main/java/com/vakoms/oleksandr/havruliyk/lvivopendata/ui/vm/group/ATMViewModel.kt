package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromATMRecord
import javax.inject.Inject

class ATMViewModel @Inject constructor(
    private var repository: ATMRepository,
    private var mapManager: MapManager
) : ViewModel() {

    private lateinit var listing: Listing<ATMRecord>

    var networkState: LiveData<NetworkState> = MutableLiveData()
    var refreshState: LiveData<NetworkState> = MutableLiveData()
    var pagedList: LiveData<PagedList<ATMRecord>> = MutableLiveData()

    private val searchString = MutableLiveData<String>()
    var searchNetworkState: LiveData<NetworkState> = MutableLiveData()
    var searchRefreshState: LiveData<NetworkState> = MutableLiveData()

    var searchPagedList: LiveData<PagedList<ATMRecord>> = Transformations.switchMap(searchString) { name ->
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

    fun addRecordsToMap(record: List<ATMRecord>) {
        mapManager.addRecords(getAddressRecordFromATMRecord(record))
    }

    fun getAllData() {
        listing = repository.getData()
        networkState = listing.networkState
        refreshState = listing.refreshState
        pagedList = listing.pagedList
    }
}