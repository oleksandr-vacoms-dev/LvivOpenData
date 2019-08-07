package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MapViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.DaggerAwareViewModelFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.MarketViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm.MarketDataViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MarketViewModel::class)
    abstract fun bindMarketViewModel(userViewModel: MarketViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketDataViewModel::class)
    abstract fun bindMarketDataViewModel(userViewModel: MarketDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(userViewModel: MapViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory): ViewModelProvider.Factory
}