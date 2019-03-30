package com.kaloglu.duels.navigation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.kaloglu.duels.BuildConfig
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.main.MainActivity
import com.kaloglu.duels.mobileui.splash.SplashActivity
import com.kaloglu.duels.utils.extensions.createIntent
import java.util.*
import javax.inject.Inject

@PerActivity
class ActivityNavigator @Inject constructor(val activity: AppCompatActivity) {

    fun finishCurrentActivity() =
            NavigationCreator(activity).finishThis()

    fun openExternalUrl(url: String) =
            NavigationCreator(activity).intent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    fun toSignInActivity(requestCodeForSignIn: Int): NavigationCreator {

        val providerList =
                Arrays.asList(
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.EmailBuilder().build()
                )

        val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                .setAvailableProviders(providerList)
                .build()

        return NavigationCreator(activity)
                .intent(intent)
                .forResult(requestCodeForSignIn)
    }

    @JvmOverloads
    fun toMainActivity(extraBuilder: (Intent.() -> Unit) = {}) =
            NavigationCreator(activity).intent(activity.createIntent<MainActivity>(extraBuilder))

    @JvmOverloads
    fun toSplashScreen(extraBuilder: (Intent.() -> Unit) = {}) =
            NavigationCreator(activity).intent(activity.createIntent<SplashActivity>(extraBuilder))

}
