package com.kaloglu.duels.mobileui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import com.firebase.ui.auth.IdpResponse
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.presentation.interfaces.splash.SplashContract
import com.kaloglu.duels.viewobjects.CachedSample

class SplashActivity : BaseMvpActivity<CachedSample, SplashContract.Presenter>(), SplashContract.View<CachedSample> {

    override val contentResourceId = R.layout.activity_splash
    override val baseFrameLayoutId = R.id.sign_in_container

    override fun initUserInterface() = Unit

    override fun onLoading() = showSnackbar("loading")

    override fun onSuccess(data: CachedSample?) = presenter.getNextActivity()

    override fun onError(errorMessage: String?, data: CachedSample?) =
            showSnackbar("hata: $errorMessage").also {
                Handler().postDelayed(presenter::getSignInActivity, 1000)
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            super.onActivityResult(requestCode, resultCode, data)
                    .also {
                        when (requestCode) {
                            presenter.requestCodeForSignIn -> handleSignInResult(IdpResponse.fromResultIntent(data), resultCode)
                        }
                    }

    private fun handleSignInResult(response: IdpResponse?, resultCode: Int) =
            when {
                resultCode == Activity.RESULT_OK -> presenter.checkAuth()
                response == null -> showSnackbar(R.string.sign_in_cancelled)
                response.error != null -> presenter.showError(response.error!!)
                else -> Unit
            }


    override fun onPresenterAttached() = presenter.checkAuth()

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, SplashActivity::class.java)

    }
}
