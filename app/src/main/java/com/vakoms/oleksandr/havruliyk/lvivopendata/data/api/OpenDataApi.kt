package com.vakoms.oleksandr.havruliyk.lvivopendata.data.api

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.BASE_URL
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.SEARCH_SQL
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.SQL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataApi {
    @GET(SEARCH_SQL)
    fun getCoordinatesByAddress(@Query(SQL) sql: String): Call<CoordinatesResponse>

    @GET(SEARCH_SQL)
    fun getFitness(@Query(SQL) sql: String): Call<FitnessResponse>

    @GET(SEARCH_SQL)
    fun getBarber(@Query(SQL) sql: String): Call<BarberResponse>

    @GET(SEARCH_SQL)
    fun getATM(@Query(SQL) sql: String): Call<ATMResponse>

    @GET(SEARCH_SQL)
    fun getCatering(@Query(SQL) sql: String): Call<CateringResponse>

    @GET(SEARCH_SQL)
    fun getMarkets(@Query(SQL) sql: String): Call<MarketsResponse>

    companion object {
        fun create(): OpenDataApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenDataApi::class.java)
    }
}