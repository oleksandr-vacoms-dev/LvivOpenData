package com.vakoms.oleksandr.havruliyk.lvivopendata.di.component

import com.vakoms.oleksandr.havruliyk.lvivopendata.OpenDataApplication
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.module.*
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        MarketModule::class,
        FitnessModule::class,
        CateringModule::class,
        BarberModule::class
    ]
)

interface AppComponent : AndroidInjector<OpenDataApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<OpenDataApplication>()
}