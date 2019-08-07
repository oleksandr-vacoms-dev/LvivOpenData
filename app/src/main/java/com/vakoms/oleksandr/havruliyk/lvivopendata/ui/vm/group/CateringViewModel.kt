package com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import javax.inject.Inject

class CateringViewModel @Inject constructor(repository: CateringRepository) : ViewModel() {
    var data: MutableLiveData<List<CateringRecord>> = repository.getAll() as MutableLiveData<List<CateringRecord>>
}