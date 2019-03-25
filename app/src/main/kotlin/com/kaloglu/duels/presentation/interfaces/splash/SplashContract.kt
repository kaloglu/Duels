package com.kaloglu.duels.presentation.interfaces.splash

import android.content.Intent
import com.firebase.ui.auth.FirebaseUiException
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView

interface SplashContract {

    interface View : MvpView {
        fun handleSignInResult(data: Intent?, resultCode: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun checkAuth()
        fun showError(firebaseUiException: FirebaseUiException)
    }
}
