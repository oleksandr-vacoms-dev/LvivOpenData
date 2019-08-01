package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule{

    @Provides
    fun providesContext(application: OpenDataApplication): Context {
        return application.applicationContext
    }
}
