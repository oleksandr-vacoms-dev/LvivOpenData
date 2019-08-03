package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.RemoteATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.ATMFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.ATMViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class ATMModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesPharmacyRepository(
            local: LocalATMDataStorage,
            remote: RemoteATMDataStorage,
            netManager: NetManager
        ): ATMRepository {
            return ATMRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalPharmacyDataStorage(context: Context)
                : LocalATMDataStorage {
            return LocalATMDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemotePharmacyDataStorage()
                : RemoteATMDataStorage {
            return RemoteATMDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun atmFragment(): ATMFragment

    @Binds
    @IntoMap
    @ViewModelKey(ATMViewModel::class)
    abstract fun bindATMViewModel(viewModel: ATMViewModel): ViewModel
}