package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.MapDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.map.MapRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.MapActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.MapViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class MapsModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesCoordinatesDataStorage(): MapDataStorage {
            return MapRepository.getInstance() as MapDataStorage
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun mapsActivity(): MapActivity

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapsViewModel(viewModel: MapViewModel): ViewModel
}