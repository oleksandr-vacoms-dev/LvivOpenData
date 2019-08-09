package com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.adapter.MarketPagingViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.test_paging.data_source.InMemoryByPageKeyRepository

class ViewModelFactory(private val repository: InMemoryByPageKeyRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarketPagingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MarketPagingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}