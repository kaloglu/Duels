package com.kaloglu.duels.domain.splash

import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.FirebaseUiException
import com.kaloglu.duels.R
import com.kaloglu.duels.domain.interfaces.splash.SplashContract
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.navigation.ActivityNavigator
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val activityNavigator: ActivityNavigator)
    : BaseAbstractPresenter<SplashContract.View>(), SplashContract.Presenter {
    override fun checkAuth() {
        when (getView().auth.currentUser != null) {
            true -> {
                activityNavigator.toMainActivity()
                        .singleTop()
                        .clearTop()
                        .finishThis()
                        .navigate()
            }
            else -> activityNavigator.toSignInActivity(getView().requestCodeForSignIn)
                    .navigate()
        }
    }

    override fun showError(error: FirebaseUiException) {
        getView().showSnackbar(
                when (error.errorCode) {
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
    }
}