package com.vakoms.oleksandr.havruliyk.lvivopendata.data.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://opendata.city-adm.lviv.ua/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }

}