package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOpenDataApi(retrofit: Retrofit): OpenDataApi {
        return retrofit.create(OpenDataApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://opendata.city-adm.lviv.ua/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}