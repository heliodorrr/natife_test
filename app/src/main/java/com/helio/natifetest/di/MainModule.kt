package com.helio.natifetest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    fun bindViewModelProviderFactory(
        factory: DaggerViewModelProviderFactory
    ): ViewModelProvider.Factory
}