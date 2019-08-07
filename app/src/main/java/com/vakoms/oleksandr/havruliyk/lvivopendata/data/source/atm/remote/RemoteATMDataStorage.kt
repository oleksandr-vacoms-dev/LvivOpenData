package com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMRecord
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.DataStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteATMDataStorage @Inject constructor(var openDataApi: OpenDataApi) : DataStorage<ATMRecord> {

    override fun getAll(): MutableLiveData<List<ATMRecord>> {
        val data = MutableLiveData<List<ATMRecord>>()
        openDataApi.getATM().enqueue(object : Callback<ATMResponse> {
            override fun onResponse(
                call: Call<ATMResponse>,
                response: Response<ATMResponse>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body().result.records
                }
            }

            override fun onFailure(call: Call<ATMResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    override fun saveAll(data: List<ATMRecord>) {

    }

    override fun deleteAll() {

    }

    override fun getById(id: Int): LiveData<ATMRecord>? {
        return null
    }
}