package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.MarketRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.component.OpenDataApp
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: OpenDataApp): Context {
        return application
    }

    @Provides
    @Reusable
    fun provideOpenDataApi(): OpenDataApi {
        return Retrofit.Builder()
            .baseUrl("https://opendata.city-adm.lviv.ua/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMarketDb(app: OpenDataApp): MarketRoomDatabase {
        return Room
            .databaseBuilder(app, MarketRoomDatabase::class.java, "market_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}