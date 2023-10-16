package com.helio.ui.ext

import android.content.res.Configuration
import androidx.fragment.app.Fragment

val Fragment.isInPortrait get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT