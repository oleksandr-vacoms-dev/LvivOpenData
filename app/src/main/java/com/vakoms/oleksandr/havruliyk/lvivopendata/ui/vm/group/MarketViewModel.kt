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

class MarketViewModel @Inject constructor(var repository: MarketRepository, var mapManager: MapManager) : ViewModel() {
    var data: MutableLiveData<List<MarketRecord>> = repository.getAll() as MutableLiveData<List<MarketRecord>>

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<MarketRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }

    fun addRecordsToMap(record: List<MarketRecord>){
        mapManager.addRecords(getAddressRecordFromMarketRecord(record))
    }
}