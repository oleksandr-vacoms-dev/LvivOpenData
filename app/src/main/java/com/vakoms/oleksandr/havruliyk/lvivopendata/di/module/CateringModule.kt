package com.vakoms.oleksandr.havruliyk.lvivopendata.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.NetManager
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.CateringRepository
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.local.LocalCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.data.source.catering.remote.RemoteCateringDataStorage
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.CateringActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.activity.data.CateringDataActivity
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.CateringDataViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.CateringViewModel
import com.vakoms.oleksandr.havruliyk.lvivopendata.ui.vm.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class CateringModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesCateringRepository(
            local: LocalCateringDataStorage,
            remote: RemoteCateringDataStorage,
            netManager: NetManager
        ): CateringRepository {
            return CateringRepository(local, remote, netManager)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesLocalCateringDataStorage(context: Context)
                : LocalCateringDataStorage {
            return LocalCateringDataStorage(context)
        }

        @JvmStatic
        @Provides
        @Singleton
        internal fun providesRemoteCateringDataStorage()
                : RemoteCateringDataStorage {
            return RemoteCateringDataStorage()
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun cateringActivity(): CateringActivity

    @Binds
    @IntoMap
    @ViewModelKey(CateringViewModel::class)
    abstract fun bindCateringViewModel(viewModel: CateringViewModel): ViewModel

    @ContributesAndroidInjector()
    internal abstract fun cateringDataActivity(): CateringDataActivity

    @Binds
    @IntoMap
    @ViewModelKey(CateringDataViewModel::class)
    abstract fun bindCateringDataViewModel(viewModel: CateringDataViewModel): ViewModel
}