package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.lifecycle.LifecycleOwner
import androidx.annotation.StringRes

interface BaseView<in T> : LifecycleOwner {
    fun showSnackbar(@StringRes messageId: Int)
    fun showSnackbar(message: String)

    fun onLoading()
    fun onSuccess(data: T?)
    fun onError(errorMessage: String?, data: T?)
}
