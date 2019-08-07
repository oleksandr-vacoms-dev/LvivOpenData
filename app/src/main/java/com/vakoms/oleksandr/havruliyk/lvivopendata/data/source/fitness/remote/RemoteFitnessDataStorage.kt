package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteFitnessDataStorage @Inject constructor(var openDataApi: OpenDataApi) : DataStorage<FitnessRecord> {

    override fun getAll(): MutableLiveData<List<FitnessRecord>> {
        val data = MutableLiveData<List<FitnessRecord>>()
        openDataApi.getFitnessCenters().enqueue(object : Callback<FitnessResponse> {
            override fun onResponse(
                call: Call<FitnessResponse>,
                response: Response<FitnessResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<FitnessResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun getByName(name: String): LiveData<List<FitnessRecord>>? {
        val data = MutableLiveData<List<FitnessRecord>>()
        openDataApi.getFitnessByName(getFitnessSql(name)).enqueue(object : Callback<FitnessResponse> {
            override fun onResponse(
                call: Call<FitnessResponse>,
                response: Response<FitnessResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<FitnessResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun saveAll(data: List<FitnessRecord>) {

    }

    override fun deleteAll() {

    }

    override fun getById(id: Int): LiveData<FitnessRecord>? {
        return null
    }

    private fun getFitnessSql(name: String): String {
        return "SELECT * from \"29782a2a-bf39-4d3b-9a6d-ac1880f2d498\"" +
                " WHERE name LIKE '%$name'"
    }
}