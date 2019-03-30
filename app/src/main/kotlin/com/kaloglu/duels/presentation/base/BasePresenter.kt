package com.kaloglu.duels.presentation.base

import androidx.annotation.CallSuper
import androidx.annotation.UiThread
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseUiException
import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView
import com.kaloglu.duels.utils.extensions.checkInjection
import java.lang.ref.WeakReference

/**
 * Base implementation for presenter
 * */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    override val genericDependencies: GenericDependencies? = null
        get() = field.checkInjection()

    private var viewRef: WeakReference<V>? = null

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: V) {
        viewRef = WeakReference(view)
        view.onPresenterAttached()
    }

    final override fun detachView() {
        viewRef?.get()?.onPresenterDetached()
        viewRef?.clear()
        viewRef = null
    }

    final override fun getView() = when {
        isViewAttached() -> viewRef?.get()!!
        else -> throw IllegalArgumentException()
    }

    final override fun isViewAttached() = viewRef != null && viewRef?.get() != null

    final override fun showFragment(fragment: BaseFragment?) {
        fragment?.let(fragmentNavigator::showFragment)
    }

    final override fun showFireBaseAuthError(firebaseUiException: FirebaseUiException) = getView().showSnackbar(
            when (firebaseUiException.errorCode) {
                ErrorCodes.UNKNOWN_ERROR -> R.string.unknown_error
                ErrorCodes.NO_NETWORK -> R.string.no_internet_connection
                ErrorCodes.PLAY_SERVICES_UPDATE_CANCELLED -> R.string.common_google_play_services_updating_text
                ErrorCodes.DEVELOPER_ERROR -> R.string.developer_error
                ErrorCodes.PROVIDER_ERROR -> R.string.provider_error
                ErrorCodes.ANONYMOUS_UPGRADE_MERGE_CONFLICT -> R.string.anonymous_upgrade_merge_error
                ErrorCodes.EMAIL_MISMATCH_ERROR -> R.string.email_mismatch_error
                else -> R.string.unknown_sign_in_response
            }
    )

    @UiThread
    @CallSuper
    override fun checkAuth() = when {
        firebaseAuth.currentUser == null -> onLogout()
        else -> onLogin()
    }

    override fun signOut() = OnCompleteListener<Void> {
        activityNavigator
                .toSplashScreen()
                .finishThis()
                .navigate()
    }

    override fun onLogin() = Unit

    override fun onLogout() = activityNavigator
            .toSignInActivity(requestCodeForSignIn)
            .navigate()
}
