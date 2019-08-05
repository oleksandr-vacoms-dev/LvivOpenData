package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.BarberRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.local.LocalBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.barber.remote.RemoteBarberDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.BarberActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.BarberDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.BarberDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.BarberViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class BarberModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesBarberRepository(
            local: LocalBarberDataStorage,
            remote: RemoteBarberDataStorage,
            netManager: NetManager
        ): BarberRepository {
            return BarberRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalBarberDataStorage(context: Context)
                : LocalBarberDataStorage {
            return LocalBarberDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemoteBarberDataStorage()
                : RemoteBarberDataStorage {
            return RemoteBarberDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun barberActivity(): BarberActivity

    @Binds
    @IntoMap
    @ViewModelKey(BarberViewModel::class)
    abstract fun bindBarberViewModel(viewModel: BarberViewModel): ViewModel

    @ContributesAndroidInjector()
    internal abstract fun barberDataActivity(): BarberDataActivity

    @Binds
    @IntoMap
    @ViewModelKey(BarberDataViewModel::class)
    abstract fun bindBarberDataViewModel(viewModel: BarberDataViewModel): ViewModel
}