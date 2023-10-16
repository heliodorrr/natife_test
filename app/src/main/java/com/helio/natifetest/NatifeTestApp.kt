package com.helio.natifetest

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.helio.natifetest.di.DaggerMainComponent
import com.helio.natifetest.di.MainComponent
import com.helio.ui.di.EntryPoint

class NatifeTestApp: Application(), EntryPoint {

    private lateinit var delegate: EntryPoint

    override fun onCreate() {
        super.onCreate()

        delegate = DaggerMainComponent.factory().create(this)
    }

    override val viewModelProviderFactory: ViewModelProvider.Factory
        get() = delegate.viewModelProviderFactory
}