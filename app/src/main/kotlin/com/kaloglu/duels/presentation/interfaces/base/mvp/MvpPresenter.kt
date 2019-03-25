package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.base.GenericDependencies

interface MvpPresenter<out V : MvpView> : LifecycleObserver {

    val genericDependencies: GenericDependencies?

    val activityNavigator
        get() = genericDependencies!!.activityNavigator

    val fragmentNavigator
        get() = genericDependencies!!.fragmentNavigator

    val requestCodeForSignIn: Int
        get() = 9999

    var uiStateManager: UIStateManager

    @UiThread
    fun attachView(view: MvpView)

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun attachLifecycle()

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachLifecycle()

    @UiThread
    fun detachView()

    @UiThread
    fun getLifeCycle(): Lifecycle

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
    fun getSignInActivity()

    @UiThread
    fun getNextActivity()

    @UiThread
    fun showFragment(fragment: BaseFragment?)

    @UiThread
    fun signOut(): OnCompleteListener<Void>
}
