package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.FitnessActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.group.data.FitnessDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.datavm.FitnessDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.groupvm.FitnessViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class FitnessModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesFitnessRepository(
            local: LocalFitnessDataStorage,
            remote: RemoteFitnessDataStorage,
            netManager: NetManager
        ): FitnessRepository {
            return FitnessRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalFitnessDataStorage(context: Context)
                : LocalFitnessDataStorage {
            return LocalFitnessDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemoteFitnessDataStorage()
                : RemoteFitnessDataStorage {
            return RemoteFitnessDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun fitnessActivity(): FitnessActivity

    @Binds
    @IntoMap
    @ViewModelKey(FitnessViewModel::class)
    abstract fun bindFitnessViewModel(viewModel: FitnessViewModel): ViewModel

    @ContributesAndroidInjector()
    internal abstract fun fitnessDataActivity(): FitnessDataActivity

    @Binds
    @IntoMap
    @ViewModelKey(FitnessDataViewModel::class)
    abstract fun bindFitnessDataViewModel(viewModel: FitnessDataViewModel): ViewModel
}