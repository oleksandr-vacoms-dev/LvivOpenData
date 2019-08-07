package com.vakoms.oleksandr.havruliyk.lvivopendata.di.component

import com.vakoms.oleksandr.havruliyk.lvivopendata.di.module.ActivitiesModule
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: OpenDataApp): Builder
    }

    fun inject(application: OpenDataApp)
}