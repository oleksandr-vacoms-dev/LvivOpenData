package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MapViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.description.*
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
    @ViewModelKey(MarketsViewModel::class)
    abstract fun bindMarketViewModel(userViewModel: MarketsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketDescriptionViewModel::class)
    abstract fun bindMarketDataViewModel(userViewModel: MarketDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FitnessesViewModel::class)
    abstract fun bindFitnessViewModel(userViewModel: FitnessesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FitnessDescriptionViewModel::class)
    abstract fun bindFitnessDataViewModel(userViewModel: FitnessDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CateringsViewModel::class)
    abstract fun bindCateringViewModel(userViewModel: CateringsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CateringDescriptionViewModel::class)
    abstract fun bindCateringDataViewModel(userViewModel: CateringDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BarbersViewModel::class)
    abstract fun bindBarberViewModel(userViewModel: BarbersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BarberDescriptionViewModel::class)
    abstract fun bindBarberDataViewModel(userViewModel: BarberDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ATMsViewModel::class)
    abstract fun bindATMViewModel(userViewModel: ATMsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ATMDescriptionViewModel::class)
    abstract fun bindATMDataViewModel(userViewModel: ATMDescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(userViewModel: MapViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory): ViewModelProvider.Factory
}