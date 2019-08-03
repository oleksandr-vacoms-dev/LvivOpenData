package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.MarketRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.MarketFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class MarketModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesMarketRepository(
            local: LocalMarketDataStorage,
            remote: RemoteMarketDataStorage,
            netManager: NetManager
        ): MarketRepository {
            return MarketRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalMarketDataStorage(context: Context)
                : LocalMarketDataStorage {
            return LocalMarketDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemoteMarketDataStorage()
                : RemoteMarketDataStorage {
            return RemoteMarketDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun marketFragment(): MarketFragment

    @Binds
    @IntoMap
    @ViewModelKey(MarketViewModel::class)
    abstract fun bindMarketViewModel(viewModel: MarketViewModel): ViewModel
}