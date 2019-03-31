package com.kaloglu.duels.mobileui.base.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView
import javax.inject.Inject

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : BaseActivity(), MvpView {

    @Inject
    lateinit var presenter: P

    protected open val snackbarLayoutId: Int = -1

    protected open val containedFragment: BaseFragment? = null

    protected open var currentFragment: BaseFragment? = null

    protected var badgeVisible: Int = -1

    /** WeakAccess */
    override fun getMvpActivity() = this

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
        savedInstanceState ?: let {
            containedFragment?.let(presenter::showFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            super.onActivityResult(requestCode, resultCode, data)
                    .also {
                        when (requestCode) {
                            presenter.requestCodeForSignIn -> handleSignInResult(data, resultCode)
                        }
                    }

    override fun initUserInterface() = Unit

    override fun onPresenterDetached() = Unit

    override fun onPresenterAttached() = presenter.checkAuth()

    override fun showSnackbar(messageId: Int) = showSnackbar(resources.getString(messageId))

    override fun showSnackbar(message: String) {
        if (snackbarLayoutId == -1)
            return

        Snackbar.make(findViewById(snackbarLayoutId), message, Snackbar.LENGTH_LONG).show()
    }

    override fun handleSignInResult(data: Intent?, resultCode: Int) {
        val response = IdpResponse.fromResultIntent(data)

        return when {
            resultCode == AppCompatActivity.RESULT_OK -> presenter.checkAuth()
            response == null -> showSnackbar(R.string.sign_in_cancelled)
            response.error != null -> presenter.showFireBaseAuthError(response.error!!)
            else -> Unit
        }
    }
}
