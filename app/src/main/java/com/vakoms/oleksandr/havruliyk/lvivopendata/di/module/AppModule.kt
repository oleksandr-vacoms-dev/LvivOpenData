package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import android.app.Application
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.component.MainActivityComponent
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }
}
