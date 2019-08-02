package com.vakoms.oleksandr.havruliyk.lvivopendata

import android.content.Context
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.FitnessRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.local.LocalFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.fitness.remote.RemoteFitnessDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.fragment.FitnessFragment
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.FitnessViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FitnessModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesFitnessViewModelFactory(repository: FitnessRepository)
                : FitnessViewModelFactory {
            return FitnessViewModelFactory(repository)
        }

        @JvmStatic
        @Provides
        internal fun providesLocalFitnessDataStorage(context: Context)
                : LocalFitnessDataStorage {
            return LocalFitnessDataStorage(context)
        }

        @JvmStatic
        @Provides
        internal fun providesRemoteFitnessDataStorage()
                : RemoteFitnessDataStorage {
            return RemoteFitnessDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun fitnessFragment(): FitnessFragment
}