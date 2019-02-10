package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner

interface BaseView : LifecycleOwner {

    fun showSnackbar(@StringRes messageId: Int)
    fun showSnackbar(message: String)

}
