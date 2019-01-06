package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface BasePresenter<M, out V : BaseView<M>> : LifecycleObserver {
    val requestCodeForSignIn: Int
        get() = 9999

    @UiThread
    fun attachView(view: BaseView<M>)

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
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

    /**
     * Gets the attached lifeCycle. You should to [isLifeCycleAttached] to avoid exceptions.
     *
     * @return the lifeCycle if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */

    /*

    @UiThread
    fun getLifeCycle(): Lifecycle

    */

    /**
     * Checks if a lifeCycle is attached to this presenter.
     *
     * @return false if no lifeCycle is attached
     */

    /*

    @UiThread
    fun isLifeCycleAttached(): Boolean

    */

    @UiThread
    fun getNextActivity()

    @UiThread
    fun getSignInActivity()
}
