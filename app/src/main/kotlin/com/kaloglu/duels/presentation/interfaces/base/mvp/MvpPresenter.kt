package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import com.firebase.ui.auth.FirebaseUiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.navigation.FragmentNavigator
import com.kaloglu.duels.presentation.base.GenericDependencies

interface MvpPresenter<V : MvpView> {

    val genericDependencies: GenericDependencies?

    val firebaseAuth: FirebaseAuth
        get() = genericDependencies!!.firebaseAuth

    val activityNavigator: ActivityNavigator
        get() = genericDependencies!!.activityNavigator

    val fragmentNavigator: FragmentNavigator
        get() = genericDependencies!!.fragmentNavigator

    val requestCodeForSignIn: Int
        get() = 9999

    @UiThread
    fun checkAuth()

    @UiThread
    fun attachView(view: V)

    @UiThread
    fun detachView()

    /**
     * Gets the attached view. You should to [isViewAttached] to avoid exceptions.
     *
     * @return the view if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */
    @UiThread
    fun getView(): V

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false if no view is attached
     */
    @UiThread
    fun isViewAttached(): Boolean

    @UiThread
    fun showFragment(fragment: BaseFragment?)

    @UiThread
    fun signOut(): OnCompleteListener<Void>

    @UiThread
    fun onLogin()

    @UiThread
    fun onLogout()

    @UiThread
    fun showFireBaseAuthError(firebaseUiException: FirebaseUiException)

}
