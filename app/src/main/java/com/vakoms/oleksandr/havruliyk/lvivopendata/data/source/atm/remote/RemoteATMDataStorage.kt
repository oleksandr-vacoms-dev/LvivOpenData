package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed.KeyedDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import javax.inject.Inject

class RemoteATMDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi
) : DataStorage<ATMRecord> {

    override fun getListing() =
        object : KeyedDataSourceFactory<ATMRecord, ATMResponse>(
            request = { page ->
                openDataApi.getATM(sqlATMs(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun getListingByName(name: String) =
        object : KeyedDataSourceFactory<ATMRecord, ATMResponse>(
            request = { page ->
                openDataApi.getATM(sqlATMs(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun get(offset: Int, amount: Int): LiveData<List<ATMRecord>> {
        val data = MutableLiveData<List<ATMRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getATM(sqlATMs(offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<ATMRecord>> {
        val data = MutableLiveData<List<ATMRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getATM(sqlATMsByName(name, offset)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getById(id: Int): LiveData<ATMRecord> {
        val data = MutableLiveData<ATMRecord>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getATM(sqlATMById(id)),
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

    override fun save(data: List<ATMRecord>) {
    }

    override fun deleteAll() {
    }
}