package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import androidx.appcompat.app.AppCompatActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.component.MainActivityComponent
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder)
            : AndroidInjector.Factory<AppCompatActivity>
}