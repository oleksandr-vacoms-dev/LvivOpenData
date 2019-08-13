package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.paging.keyed.KeyedDataSourceFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import javax.inject.Inject

class RemoteFitnessDataStorage @Inject constructor(
    private val openDataApi: OpenDataApi
) : DataStorage<FitnessRecord> {

    override fun getListing() =
        object : KeyedDataSourceFactory<FitnessRecord, FitnessResponse>(
            request = { page ->
                openDataApi.getFitness(sqlFitnesses(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun getListingByName(name: String) =
        object : KeyedDataSourceFactory<FitnessRecord, FitnessResponse>(
            request = { page ->
                openDataApi.getFitness(sqlFitnesses(page, PAGE_SIZE))
            },
            onResponse = { response ->
                response?.result?.records!!
            }
        ) {}.getListing()

    override fun get(offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        val data = MutableLiveData<List<FitnessRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getFitness(sqlFitnesses(offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getByName(name: String, offset: Int, amount: Int): LiveData<List<FitnessRecord>> {
        val data = MutableLiveData<List<FitnessRecord>>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getFitness(sqlFitnessesByName(name, offset, amount)),
            onSuccess = { response ->
                data.value = response?.result?.records!!
            },
            onError = {
                data.value = null
            }
        )
        return data
    }

    override fun getById(id: Int): LiveData<FitnessRecord> {
        val data = MutableLiveData<FitnessRecord>()

        ApiRequestHelper.asyncRequest(
            request = openDataApi.getFitness(sqlFitnessById(id)),
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

    override fun save(data: List<FitnessRecord>) {
    }

    override fun deleteAll() {
    }
}
