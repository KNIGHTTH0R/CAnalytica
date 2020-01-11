package com.android.rbcanalytica.dagger

import androidx.lifecycle.ViewModelProvider
import com.android.rbcanalytica.dagger.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}