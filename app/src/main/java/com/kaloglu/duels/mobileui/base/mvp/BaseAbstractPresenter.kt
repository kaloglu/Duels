package com.kaloglu.duels.mobileui.base.mvp

import android.support.annotation.CallSuper
import com.kaloglu.duels.domain.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.domain.interfaces.base.mvp.BaseView
import java.lang.ref.WeakReference

abstract class BaseAbstractPresenter<V : BaseView> : BasePresenter<V> {

    private var viewRef: WeakReference<V>? = null

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun attachView(view: BaseView) {
        viewRef = WeakReference(view as V)
    }

    @CallSuper
    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    /**
     * Gets the attached view. You should call [isViewAttached] to avoid exceptions.
     *
     * @return the view if it is attached
     * @throws [IllegalArgumentException] if no view is attached
     */
    override fun getView() = when {
        isViewAttached() -> viewRef!!.get()!!
        else -> throw IllegalArgumentException()
    }

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false if no view is attached
     */
    override fun isViewAttached() = viewRef != null && viewRef!!.get() != null
}
