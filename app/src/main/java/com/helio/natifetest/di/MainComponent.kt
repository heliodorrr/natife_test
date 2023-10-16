package com.helio.natifetest.di

import android.app.Application
import com.helio.impl.di.ImplModule
import com.helio.ui.di.EntryPoint
import com.helio.ui.di.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    MainModule::class,
    ImplModule::class,
    ViewModelsModule::class,
])
@Singleton
interface MainComponent: EntryPoint {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MainComponent
    }




}

