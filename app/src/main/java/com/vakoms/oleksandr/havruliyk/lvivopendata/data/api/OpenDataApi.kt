package com.vakoms.oleksandr.havruliyk.lvivopendata.data.api

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDataApi {
    @GET("$SEARCH?$ID=$MARKET_ID")
    fun getMarkets(): Call<MarketsResponse>

    @GET("$SEARCH?$ID=$FITNESS_ID")
    fun getFitness(): Call<FitnessResponse>

    @GET("$SEARCH?$ID=$CATERING_ID")
    fun getCatering(): Call<CateringResponse>

    @GET("$SEARCH?$ID=$ATM_ID")
    fun getATM(): Call<ATMResponse>

    @GET("$SEARCH?$ID=$BARBER_ID")
    fun getBarber(): Call<BarberResponse>

    @GET(SEARCH_SQL)
    fun getCoordinatesByAddress(@Query(SQL) sql: String): Call<CoordinatesResponse>

    @GET(SEARCH_SQL)
    fun getMarketByName(@Query(SQL) sql: String): Call<MarketsResponse>

    @GET(SEARCH_SQL)
    fun getFitnessByName(@Query(SQL) sql: String): Call<FitnessResponse>

    @GET(SEARCH_SQL)
    fun getBarberByName(@Query(SQL) sql: String): Call<BarberResponse>

    @GET(SEARCH_SQL)
    fun getATMByName(@Query(SQL) sql: String): Call<ATMResponse>

    @GET(SEARCH_SQL)
    fun getCateringByName(@Query(SQL) sql: String): Call<CateringResponse>
}