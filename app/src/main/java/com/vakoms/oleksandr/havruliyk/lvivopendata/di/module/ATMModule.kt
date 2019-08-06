package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.ATMRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.local.LocalATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.atm.remote.RemoteATMDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.ATMActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.data.ATMDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm.ATMDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.ATMViewModel
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
        internal fun providesATMRepository(
            local: LocalATMDataStorage,
            remote: RemoteATMDataStorage,
            netManager: NetManager
        ): ATMRepository {
            return ATMRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalATMDataStorage(context: Context)
                : LocalATMDataStorage {
            return LocalATMDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemoteATMDataStorage()
                : RemoteATMDataStorage {
            return RemoteATMDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun atmActivity(): ATMActivity

    @Binds
    @IntoMap
    @ViewModelKey(ATMViewModel::class)
    abstract fun bindATMViewModel(viewModel: ATMViewModel): ViewModel

    @ContributesAndroidInjector()
    internal abstract fun atmDataActivity(): ATMDataActivity

    @Binds
    @IntoMap
    @ViewModelKey(ATMDataViewModel::class)
    abstract fun bindATMDataViewModel(viewModel: ATMDataViewModel): ViewModel
}