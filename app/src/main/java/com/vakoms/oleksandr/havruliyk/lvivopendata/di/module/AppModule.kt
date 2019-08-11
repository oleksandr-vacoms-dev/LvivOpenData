package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.room.Room
import com.vakoms.oleksandr.havruliyk.lvivopendata.OpenDataApp
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.api.OpenDataApi
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.ATMRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.BarberRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.CateringRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.FitnessRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.MarketRoomDatabase
import com.vakoms.oleksandr.havruliyk.lvivopendata.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
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
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Reusable
    fun provideOpenDataApi(): OpenDataApi {
        return OpenDataApi.create()
    }

    @Singleton
    @Provides
    fun provideMarketDb(app: OpenDataApp): MarketRoomDatabase {
        return Room
            .databaseBuilder(app, MarketRoomDatabase::class.java, "market_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFitnessDb(app: OpenDataApp): FitnessRoomDatabase {
        return Room
            .databaseBuilder(app, FitnessRoomDatabase::class.java, "fitness_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCateringDb(app: OpenDataApp): CateringRoomDatabase {
        return Room
            .databaseBuilder(app, CateringRoomDatabase::class.java, "catering_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBarberDb(app: OpenDataApp): BarberRoomDatabase {
        return Room
            .databaseBuilder(app, BarberRoomDatabase::class.java, "barber_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideATMDb(app: OpenDataApp): ATMRoomDatabase {
        return Room
            .databaseBuilder(app, ATMRoomDatabase::class.java, "atm_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}