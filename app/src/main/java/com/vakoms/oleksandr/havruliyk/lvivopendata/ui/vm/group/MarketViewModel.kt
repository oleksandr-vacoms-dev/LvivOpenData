package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import javax.inject.Inject

class MarketViewModel @Inject constructor(repository: MarketRepository) : ViewModel() {
    var data: MutableLiveData<List<MarketRecord>> = repository.getAll() as MutableLiveData<List<MarketRecord>>

    private val searchString = MutableLiveData<String>()
    val searchData: LiveData<List<MarketRecord>> = Transformations.switchMap(searchString) { name ->
        repository.getByName(name)
    }

    fun setSearchData(search: String) {
        searchString.value = search
    }
}