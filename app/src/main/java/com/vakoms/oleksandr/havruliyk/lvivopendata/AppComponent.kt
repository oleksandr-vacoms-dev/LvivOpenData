package com.vakoms.oleksandr.havruliyk.lvivopendata

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MarketModule::class,
        FitnessModule::class
    ]
)

interface AppComponent : AndroidInjector<OpenDataApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<OpenDataApplication>()
}