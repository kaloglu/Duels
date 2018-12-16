package com.kaloglu.duels.mobileui.base.mvp

import android.arch.lifecycle.Lifecycle
import android.support.annotation.CallSuper
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import java.lang.ref.WeakReference

abstract class BaseAbstractPresenter<M, V : BaseView<M>>(
        val activityNavigator: ActivityNavigator
) : BasePresenter<M, V> {

    private var viewRef: WeakReference<V>? = null
    private var viewLifecycleRef: WeakReference<Lifecycle?>? = null

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun attachView(view: BaseView<M>) {
        viewRef = WeakReference(view as V)
        viewLifecycleRef = WeakReference(viewRef?.get()?.lifecycle)

        viewLifecycleRef?.get()?.addObserver(this)

    }

    @CallSuper
    override fun detachView() {
        viewRef?.clear()
        viewRef = null
        viewLifecycleRef = null
    }

    override fun getView() = when {
        isViewAttached() -> viewRef!!.get()!!
        else -> throw IllegalArgumentException()
    }

    override fun isViewAttached() = viewRef != null && viewRef?.get() != null

    override fun getSignInActivity() {
        activityNavigator
                .toSignInActivity(requestCodeForSignIn)
                .navigate()
    }

}
