package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed.KeyedDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import javax.inject.Inject

class RemoteBarberDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi
) : DataStorage<BarberRecord> {

    override fun getListing() =
        object : KeyedDataSourceFactory<BarberRecord, BarberResponse>(
            request = { page ->
                openDataApi.getBarber(sqlBarbers(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun getListingByName(name: String) =
        object : KeyedDataSourceFactory<BarberRecord, BarberResponse>(
            request = { page ->
                openDataApi.getBarber(sqlBarbers(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun get(offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        val data = MutableLiveData<List<BarberRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getBarber(sqlBarbers(offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<BarberRecord>> {
        val data = MutableLiveData<List<BarberRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getBarber(sqlBarbersByName(name, offset)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getById(id: Int): LiveData<BarberRecord> {
        val data = MutableLiveData<BarberRecord>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getBarber(sqlBarberById(id)),
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

    override fun save(data: List<BarberRecord>) {
    }

    override fun deleteAll() {
    }
}