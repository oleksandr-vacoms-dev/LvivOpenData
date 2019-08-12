package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.Listing
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.NetworkState
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromFitnessRecord
import javax.inject.Inject

class FitnessViewModel @Inject constructor(
    private var repository: FitnessRepository,
    private var mapManager: MapManager
) : ViewModel() {

    private lateinit var listing: Listing<FitnessRecord>

    var networkState: LiveData<NetworkState> = MutableLiveData()
    var refreshState: LiveData<NetworkState> = MutableLiveData()
    var pagedList: LiveData<PagedList<FitnessRecord>> = MutableLiveData()

    private val searchString = MutableLiveData<String>()
    var searchNetworkState: LiveData<NetworkState> = MutableLiveData()
    var searchRefreshState: LiveData<NetworkState> = MutableLiveData()

    var searchPagedList: LiveData<PagedList<FitnessRecord>> = Transformations.switchMap(searchString) { name ->
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

    fun addRecordsToMap(record: List<FitnessRecord>) {
        mapManager.addRecords(getAddressRecordFromFitnessRecord(record))
    }

    fun getAllData() {
        listing = repository.getData()
        networkState = listing.networkState
        refreshState = listing.refreshState
        pagedList = listing.pagedList
    }
}