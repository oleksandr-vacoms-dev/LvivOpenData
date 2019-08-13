package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.description.*
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MarketActivityModule::class)])
    abstract fun bindMarketActivity(): MarketsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MarketDataActivityModule::class)])
    abstract fun bindMarketDataActivity(): MarketDescriptionActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FitnessActivityModule::class)])
    abstract fun bindFitnessActivity(): FitnessesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FitnessDataActivityModule::class)])
    abstract fun bindFitnessDataActivity(): FitnessDescriptionActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CateringActivityModule::class)])
    abstract fun bindCateringActivity(): CateringsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CateringDataActivityModule::class)])
    abstract fun bindCateringDataActivity(): CateringDescriptionActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(BarberActivityModule::class)])
    abstract fun bindBarberActivity(): BarbersActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(BarberDataActivityModule::class)])
    abstract fun bindBarberDataActivity(): BarberDescriptionActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ATMActivityModule::class)])
    abstract fun bindATMActivity(): ATMsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ATMDataActivityModule::class)])
    abstract fun bindATMDataActivity(): ATMDescriptionActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MapActivityModule::class)])
    abstract fun bindMapActivity(): MapActivity
}

@Module
abstract class MarketActivityModule {
    @ContributesAndroidInjector()
    abstract fun marketActivity(): MarketsActivity
}

@Module
abstract class MarketDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun marketDataActivity(): MarketDescriptionActivity
}

@Module
abstract class FitnessActivityModule {
    @ContributesAndroidInjector()
    abstract fun fitnassActivity(): FitnessesActivity
}

@Module
abstract class FitnessDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun fitnessDataActivity(): FitnessDescriptionActivity
}

@Module
abstract class CateringActivityModule {
    @ContributesAndroidInjector()
    abstract fun cateringActivity(): CateringsActivity
}

@Module
abstract class CateringDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun cateringDataActivity(): CateringDescriptionActivity
}

@Module
abstract class BarberActivityModule {
    @ContributesAndroidInjector()
    abstract fun BarberActivity(): BarbersActivity
}

@Module
abstract class BarberDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun barberDataActivity(): BarberDescriptionActivity
}

@Module
abstract class ATMActivityModule {
    @ContributesAndroidInjector()
    abstract fun atmActivity(): ATMsActivity
}

@Module
abstract class ATMDataActivityModule {
    @ContributesAndroidInjector()
    abstract fun atmDataActivity(): ATMDescriptionActivity
}

@Module
abstract class MapActivityModule {
    @ContributesAndroidInjector()
    abstract fun mapActivity(): MapActivity
}