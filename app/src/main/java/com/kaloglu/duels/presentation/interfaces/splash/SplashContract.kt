package com.kaloglu.duels.presentation.interfaces.splash

import android.content.Intent
import com.firebase.ui.auth.FirebaseUiException
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityPresenter
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityView

interface SplashContract {

    interface View : ActivityView {
        fun handleSignInResult(data: Intent?, resultCode: Int)
    }

    interface Presenter : ActivityPresenter<View> {
        fun checkAuth()
        fun showError(firebaseUiException: FirebaseUiException)
    }
}
