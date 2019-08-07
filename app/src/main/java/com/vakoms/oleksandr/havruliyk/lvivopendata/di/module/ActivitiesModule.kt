package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data.*
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MarketActivityModule::class)])
    abstract fun bindMarketActivity(): MarketActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MarketDataActivityModule::class)])
    abstract fun bindMarketDataActivity(): MarketDataActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FitnessActivityModule::class)])
    abstract fun bindFitnessActivity(): FitnessActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FitnessDataActivityModule::class)])
    abstract fun bindFitnessDataActivity(): FitnessDataActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CateringActivityModule::class)])
    abstract fun bindCateringActivity(): CateringActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CateringDataActivityModule::class)])
    abstract fun bindCateringDataActivity(): CateringDataActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(BarberActivityModule::class)])
    abstract fun bindBarberActivity(): BarberActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(BarberDataActivityModule::class)])
    abstract fun bindBarberDataActivity(): BarberDataActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ATMActivityModule::class)])
    abstract fun bindATMActivity(): ATMActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ATMDataActivityModule::class)])
    abstract fun bindATMDataActivity(): ATMDataActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MapActivityModule::class)])
    abstract fun bindMapActivity(): MapActivity
}

@Module
abstract class MarketActivityModule {
    @ContributesAndroidInjector()
    abstract fun marketActivity(): MarketActivity
}

@Module
abstract class MarketDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun marketDataActivity(): MarketDataActivity
}

@Module
abstract class FitnessActivityModule {
    @ContributesAndroidInjector()
    abstract fun fitnassActivity(): FitnessActivity
}

@Module
abstract class FitnessDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun fitnessDataActivity(): FitnessDataActivity
}

@Module
abstract class CateringActivityModule {
    @ContributesAndroidInjector()
    abstract fun cateringActivity(): CateringActivity
}

@Module
abstract class CateringDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun cateringDataActivity(): CateringDataActivity
}

@Module
abstract class BarberActivityModule {
    @ContributesAndroidInjector()
    abstract fun BarberActivity(): BarberActivity
}

@Module
abstract class BarberDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun barberDataActivity(): BarberDataActivity
}

@Module
abstract class ATMActivityModule {
    @ContributesAndroidInjector()
    abstract fun atmActivity(): ATMActivity
}

@Module
abstract class ATMDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun atmDataActivity(): ATMDataActivity
}

@Module
abstract class MapActivityModule {
    @ContributesAndroidInjector()
    abstract fun mapActivity(): MapActivity
}