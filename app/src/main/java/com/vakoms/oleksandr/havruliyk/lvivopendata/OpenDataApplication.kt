package com.vakoms.oleksandr.havruliyk.lvivopendata

import androidx.fragment.app.Fragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class OpenDataApplication : DaggerApplication(), HasSupportFragmentInjector {

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }
}