package com.helio.ui.di

import androidx.lifecycle.ViewModelProvider

interface EntryPoint {
    val viewModelProviderFactory: ViewModelProvider.Factory
}