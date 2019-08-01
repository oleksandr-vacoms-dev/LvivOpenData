package com.vakoms.oleksandr.havruliyk.lvivopendata.data.api

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.barber.BarberResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.catering.CateringResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.fitnesscenters.FitnessCentersResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.market.MarketsResponse
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.model.pharmacy.PharmacyResponse
import retrofit2.Call
import retrofit2.http.GET

interface OpenDataApi {
    @GET("3/action/datastore_search?resource_id=2fc42d58-a332-4234-bc19-152d51f816a1")
    fun getMarkets(): Call<MarketsResponse>

    @GET("3/action/datastore_search?resource_id=29782a2a-bf39-4d3b-9a6d-ac1880f2d498")
    fun getFitnessCenters(): Call<FitnessCentersResponse>

    @GET("3/action/datastore_search?resource_id=a656bf70-fde7-404c-9528-7100401040b2")
    fun getCatering(): Call<CateringResponse>

    @GET("3/action/datastore_search?resource_id=6780cfe4-ab1a-456b-821a-fbc1c963c8ef")
    fun getPharmacy(): Call<PharmacyResponse>

    @GET("3/action/datastore_search?resource_id=634c8a6d-c272-4375-bd29-92526722b7ac")
    fun getBarber(): Call<BarberResponse>
}