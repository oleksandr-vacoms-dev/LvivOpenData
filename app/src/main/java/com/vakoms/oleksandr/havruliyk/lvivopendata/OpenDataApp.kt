package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.app.Activity
import android.app.Application
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.component.AppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class OpenDataApp : Application(), HasActivityInjector {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}