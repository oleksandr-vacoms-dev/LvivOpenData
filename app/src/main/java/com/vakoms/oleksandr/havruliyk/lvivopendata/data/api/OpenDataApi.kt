package com.vakoms.oleksandr.havruliyk.lvivopendata.data.api

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import retrofit2.Call
import retrofit2.http.GET

interface OpenDataApi {
    @GET("3/action/datastore_search?resource_id=2fc42d58-a332-4234-bc19-152d51f816a1")
    fun getMarkets(): Call<MarketsResponse>

    @GET("3/action/datastore_search?resource_id=29782a2a-bf39-4d3b-9a6d-ac1880f2d498")
    fun getFitnessCenters(): Call<FitnessCentersResponse>
}