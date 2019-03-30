package com.kaloglu.duels.presentation.interfaces.base.mvp

import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.kaloglu.duels.domain.model.base.BaseModel
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.base.GenericListDependencies

interface MvpListPresenter<M : BaseModel, V : MvpListView<M>>
    : MvpPresenter<V>, UIStateManager.UIStatesPresenter, LifecycleObserver {

    override val genericDependencies: GenericListDependencies?

    val uiStateManager: UIStateManager
        get() = genericDependencies!!.uiStateManager

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun attachLifecycle()

    @UiThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachLifecycle()

    @UiThread
    fun getLifeCycle(): Lifecycle

    @UiThread
    fun observe()

    @UiThread
    fun remove(model: M)

    @UiThread
    fun openDetail(model: M)
}
