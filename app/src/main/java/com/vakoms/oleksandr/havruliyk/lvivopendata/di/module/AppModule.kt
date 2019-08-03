package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import com.vakoms.oleksandr.havruliyk.lvivopendata.OpenDataApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: OpenDataApplication): Context {
        return application.applicationContext
    }
}
