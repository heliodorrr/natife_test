package com.helio.ui

import androidx.viewbinding.ViewBinding

abstract class BindingAwareFragment<T: ViewBinding>: InjectableFragment() {

    protected abstract var viewBinding: T?

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}

