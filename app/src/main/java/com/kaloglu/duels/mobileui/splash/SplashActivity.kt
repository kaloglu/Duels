package com.kaloglu.duels.mobileui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.IdpResponse
import com.kaloglu.duels.R
import com.kaloglu.duels.domain.interfaces.splash.SplashContract
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity

class SplashActivity : BaseMvpActivity<SplashContract.Presenter>(),
        SplashContract.View {

    override val contentResourceId = R.layout.activity_splash
    override val baseFrameLayoutId = R.id.sign_in_container

    override fun initUserInterface() = Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeForSignIn) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                presenter.checkAuth()
            } else {
                if (response == null) {
                    showSnackbar(R.string.sign_in_cancelled)
                    return
                }
                if (response.error != null)
                    presenter.showError(response.error!!)
            }
        }
    }


    override fun onPresenterAttached() = presenter.checkAuth()

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, SplashActivity::class.java)

    }
}
