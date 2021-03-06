package com.kaloglu.duels.presentation.interfaces.base.mvp

import android.content.Intent
import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.lifecycle.LifecycleOwner
import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.presentation.interfaces.base.bottomsheetmenu.BottomSheetMenu

interface MvpView : LifecycleOwner {

    @UiThread
    fun showSnackbar(@StringRes messageId: Int)

    @UiThread
    fun showSnackbar(message: String)

    @UiThread
    fun handleSignInResult(data: Intent?, resultCode: Int)

    @UiThread
    fun onPresenterAttached()

    @UiThread
    fun onPresenterDetached()

    @UiThread
    fun getMvpActivity(): MvpView
}
