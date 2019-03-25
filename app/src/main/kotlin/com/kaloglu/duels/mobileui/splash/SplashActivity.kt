package com.kaloglu.duels.mobileui.splash

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.IdpResponse
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.presentation.interfaces.splash.SplashContract

class SplashActivity : BaseMvpActivity<SplashContract.Presenter>(), SplashContract.View {

    override val contentResourceId = R.layout.activity_splash

    override val baseFrameLayoutId = R.id.sign_in_container

    override val snackbarLayoutId: Int = R.id.sign_in_container

    override fun initUserInterface() = Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            super.onActivityResult(requestCode, resultCode, data)
                    .also {
                        when (requestCode) {
                            presenter.requestCodeForSignIn -> handleSignInResult(data, resultCode)
                        }
                    }

    override fun handleSignInResult(data: Intent?, resultCode: Int) {
        val response = IdpResponse.fromResultIntent(data)

        return when {
            resultCode == AppCompatActivity.RESULT_OK -> presenter.checkAuth()
            response == null -> showSnackbar(R.string.sign_in_cancelled)
            response.error != null -> presenter.showError(response.error!!)
            else -> Unit
        }
    }

    override fun onPresenterAttached() = presenter.checkAuth()

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, SplashActivity::class.java)

    }
}