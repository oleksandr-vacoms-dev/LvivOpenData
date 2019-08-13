package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed.KeyedDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import javax.inject.Inject

class RemoteCateringDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi
) : DataStorage<CateringRecord> {

    override fun getListing() =
        object : KeyedDataSourceFactory<CateringRecord, CateringResponse>(
            request = { page ->
                openDataApi.getCatering(sqlCaterings(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun getListingByName(name: String) =
        object : KeyedDataSourceFactory<CateringRecord, CateringResponse>(
            request = { page ->
                openDataApi.getCatering(sqlCaterings(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun get(offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        val data = MutableLiveData<List<CateringRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getCatering(sqlCaterings(offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<CateringRecord>> {
        val data = MutableLiveData<List<CateringRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getCatering(sqlCateringsByName(name, offset)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getById(id: Int): LiveData<CateringRecord> {
        val data = MutableLiveData<CateringRecord>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getCatering(sqlCateringById(id)),
            onSuccess = { response ->
                with(response?.result?.records!!) {
                    if (this.isNotEmpty()) {
                        data.value = this[0]
                    }
                }
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun save(data: List<CateringRecord>) {
    }

    override fun deleteAll() {
    }
}