package com.kaloglu.duels.domain.interfaces.splash

import com.firebase.ui.auth.FirebaseUiException
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.domain.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.domain.interfaces.base.mvp.BaseView

interface SplashContract {

    interface View : BaseView {
        val requestCodeForSignIn: Int
            get() = 9999

        val auth: FirebaseAuth
            get() = FirebaseAuth.getInstance()!!
    }

    interface Presenter : BasePresenter<View> {
        fun checkAuth()
        fun showError(error: FirebaseUiException)
    }
}
