package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.content.Context
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.MarketRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.local.LocalMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.market.remote.RemoteMarketDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.MarketFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MarketViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MarketModule {


    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesMarketViewModelFactory(repository: MarketRepository)
                : MarketViewModelFactory {
            return MarketViewModelFactory(repository)
        }

        @JvmStatic
        @Provides
        internal fun providesLocalMarketDataStorage(context: Context)
                : LocalMarketDataStorage {
            return LocalMarketDataStorage(context)
        }

        @JvmStatic
        @Provides
        internal fun providesRemoteMarketDataStorage()
                : RemoteMarketDataStorage {
            return RemoteMarketDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun marketFragment(): MarketFragment
}