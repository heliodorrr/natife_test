package com.helio.ui

import android.content.Context
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.helio.ui.di.EntryPoint

open class InjectableFragment: Fragment() {

    private var _factory: ViewModelProvider.Factory? = null
    val factory: ViewModelProvider.Factory
        get() = _factory!!

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        _factory = (context.applicationContext as EntryPoint).viewModelProviderFactory
    }




}