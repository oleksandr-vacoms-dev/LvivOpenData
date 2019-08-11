package com.vakoms.oleksandr.havruliyk.lvivopendata.data.api

import android.util.Log
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.atm.ATMResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitness.FitnessResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.map.CoordinatesResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @GET(SEARCH_SQL)
    fun getMarkets(@Query(SQL) sql: String): Call<MarketsResponse>

    @GET(SEARCH)
    fun get(@Query(ID) id: String, @Query(OFFSET) offset: Int): Call<MarketsResponse>

    companion object {
        fun create(): OpenDataApi = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): OpenDataApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenDataApi::class.java)
        }
    }
}