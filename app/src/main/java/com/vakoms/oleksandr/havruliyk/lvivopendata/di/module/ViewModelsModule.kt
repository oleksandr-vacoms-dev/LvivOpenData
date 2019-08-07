package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MapViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.data.*
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.factory.DaggerAwareViewModelFactory
import com.vakoms.oleksandr.havruliyk.lvivopendata.di.factory.ViewModelKey
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.group.*
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
    @ViewModelKey(FitnessViewModel::class)
    abstract fun bindFitnessViewModel(userViewModel: FitnessViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FitnessDataViewModel::class)
    abstract fun bindFitnessDataViewModel(userViewModel: FitnessDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CateringViewModel::class)
    abstract fun bindCateringViewModel(userViewModel: CateringViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CateringDataViewModel::class)
    abstract fun bindCateringDataViewModel(userViewModel: CateringDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BarberViewModel::class)
    abstract fun bindBarberViewModel(userViewModel: BarberViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BarberDataViewModel::class)
    abstract fun bindBarberDataViewModel(userViewModel: BarberDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ATMViewModel::class)
    abstract fun bindATMViewModel(userViewModel: ATMViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ATMDataViewModel::class)
    abstract fun bindATMDataViewModel(userViewModel: ATMDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(userViewModel: MapViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory): ViewModelProvider.Factory
}