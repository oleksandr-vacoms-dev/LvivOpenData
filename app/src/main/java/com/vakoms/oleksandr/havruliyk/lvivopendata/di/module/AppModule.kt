package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.app.Application
import com.vakoms.oleksandr.havruliyk.lvivopendata.OpenDataApp
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.ATMRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.BarberRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.CateringRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.FitnessRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.MarketRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: OpenDataApp): Application = application

    @Provides
    @Reusable
    fun provideExecutor(): Executor = Executors.newSingleThreadExecutor()


    @Provides
    @Reusable
    fun provideOpenDataApi() = OpenDataApi.create()

    @Singleton
    @Provides
    fun provideMarketDb(app: OpenDataApp) = MarketRoomDatabase.create(app)

    @Singleton
    @Provides
    fun provideFitnessDb(app: OpenDataApp) = FitnessRoomDatabase.create(app)

    @Singleton
    @Provides
    fun provideCateringDb(app: OpenDataApp) = CateringRoomDatabase.create(app)

    @Singleton
    @Provides
    fun provideBarberDb(app: OpenDataApp) = BarberRoomDatabase.create(app)

    @Singleton
    @Provides
    fun provideATMDb(app: OpenDataApp) = ATMRoomDatabase.create(app)
}