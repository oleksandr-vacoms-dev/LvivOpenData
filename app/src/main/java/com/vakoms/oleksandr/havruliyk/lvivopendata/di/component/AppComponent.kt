package com.vakoms.oleksandr.havruliyk.lvivopendata.di.component

import android.app.Application
import com.vakoms.oleksandr.havruliyk.lvivopendata.OpenDataApp
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.module.*
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
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
        BarberModule::class,
        ATMModule::class,
        MapsModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: OpenDataApp)
}