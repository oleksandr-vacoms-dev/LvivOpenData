package com.vakoms.oleksandr.havruliyk.lvivopendata.di.component

import com.vakoms.oleksandr.havruliyk.lvivopendata.di.module.MainActivityModule
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MainActivity
import dagger.android.AndroidInjector
import dagger.Subcomponent


@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
