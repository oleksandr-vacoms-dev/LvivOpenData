package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.MarketActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.data.MarketDataActivity
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
abstract class MapActivityModule {
    @ContributesAndroidInjector()
    abstract fun mapActivity(): MapActivity
}
