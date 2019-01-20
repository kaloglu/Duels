package com.kaloglu.duels.mobileui.base.mvp

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import java.lang.ref.WeakReference

abstract class BaseAbstractPresenter<M, V : BaseView<M>> : BasePresenter<M, V> {

    abstract val activityNavigator: ActivityNavigator
    open lateinit var uiStateManager: UIStateManager

    private var viewRef: WeakReference<V>? = null
    private var viewLifecycleRef: WeakReference<Lifecycle?>? = null

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun attachView(view: BaseView<M>) {
        viewRef = WeakReference(view as V)
        viewLifecycleRef = WeakReference(viewRef?.get()?.lifecycle)

        viewLifecycleRef?.get()?.addObserver(this)

        if (view is UIStateManager.UIStates) {
            uiStateManager = UIStateManager(view.getContext()!!)
            uiStateManager.initStates(view)
        }
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

    override fun loadingUIState() = uiStateManager.loadingUIState()

    override fun emptyUIState() = uiStateManager.emptyUIState()

    override fun contentUIState() = uiStateManager.contentUIState()

    override fun errorUIState() = uiStateManager.errorUIState()

}
