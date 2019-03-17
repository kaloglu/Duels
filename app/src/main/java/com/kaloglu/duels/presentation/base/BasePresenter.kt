package com.kaloglu.duels.presentation.base

import androidx.annotation.CallSuper
import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView
import com.kaloglu.duels.utils.extensions.checkInjection
import java.lang.ref.WeakReference

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    override val genericDependencies: GenericDependencies? = null
        get() = GenericDependencies::class.java.checkInjection(field)

    open var uiStateManager: UIStateManager? = null

    private var viewRef: WeakReference<V>? = null

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: MvpView) {
        viewRef = WeakReference(view as V)
        if (view is UIStateManager.UIStatesView) {
            uiStateManager?.initStates(view)
        }
    }

    @CallSuper
    override fun attachLifecycle() =
            getLifeCycle().addObserver(this)

    @CallSuper
    override fun detachLifecycle() =
            getLifeCycle().removeObserver(this)

    @CallSuper
    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    override fun getView() = when {
        isViewAttached() -> viewRef!!.get()!!
        else -> throw IllegalArgumentException()
    }

    override fun isViewAttached() = viewRef != null && viewRef?.get() != null

    override fun getLifeCycle() = getView().lifecycle

    override fun signOut(): OnCompleteListener<Void> =
            OnCompleteListener {
                genericDependencies!!
                        .activityNavigator
                        .toSplashScreen()
                        .finishThis()
                        .navigate()
            }

    override fun getNextActivity() {
        TODO("getNextActivity() should override at ${this.javaClass.name}")
    }

    override fun getSignInActivity() {
        activityNavigator
                .toSignInActivity(requestCodeForSignIn)
                .navigate()
    }

    override fun showFragment(fragment: BaseFragment?) {
        fragment?.let {
            fragmentNavigator
                    .showFragment(fragment)
        }
    }

}
