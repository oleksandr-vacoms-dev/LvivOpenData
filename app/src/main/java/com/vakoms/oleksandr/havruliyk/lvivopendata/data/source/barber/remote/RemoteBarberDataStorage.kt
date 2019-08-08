package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.barberSql
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteBarberDataStorage @Inject constructor(var openDataApi: OpenDataApi) : DataStorage<BarberRecord> {

    override fun getAll(): MutableLiveData<List<BarberRecord>> {
        val data = MutableLiveData<List<BarberRecord>>()
        openDataApi.getBarber().enqueue(object : Callback<BarberResponse> {
            override fun onResponse(
                call: Call<BarberResponse>,
                response: Response<BarberResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<BarberResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun getByName(bankLabel: String): LiveData<List<BarberRecord>>? {
        val data = MutableLiveData<List<BarberRecord>>()
        openDataApi.getBarberByName(barberSql(bankLabel)).enqueue(object : Callback<BarberResponse> {
            override fun onResponse(
                call: Call<BarberResponse>,
                response: Response<BarberResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<BarberResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data    }

    override fun saveAll(data: List<BarberRecord>) {

    }

    override fun deleteAll() {

    }

    override fun getById(id: Int): LiveData<BarberRecord>? {
        return null
    }
}