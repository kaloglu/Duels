package com.kaloglu.duels.domain.interfaces.base.mvp

import android.support.annotation.StringRes

interface BaseView {
    fun showSnackbar(@StringRes messageId: Int)
    fun showSnackbar(message: String)
}
