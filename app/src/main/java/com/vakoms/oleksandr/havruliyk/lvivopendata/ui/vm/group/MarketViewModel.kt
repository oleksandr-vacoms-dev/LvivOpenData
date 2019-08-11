package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.manager.MapManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.getAddressRecordFromMarketRecord
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    var repository: MarketRepository,
    var mapManager: MapManager
) : ViewModel() {

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<MarketRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    private val itemResult = repository.getData()
    val pagedList = itemResult.pagedList
    val networkState = itemResult.networkState
    val refreshState = itemResult.refreshState

    fun refresh() {
        itemResult.refresh.invoke()
    }

    fun retry() {
        itemResult.retry.invoke()
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<MarketRecord>) {
        mapManager.addRecords(getAddressRecordFromMarketRecord(record))
    }
}