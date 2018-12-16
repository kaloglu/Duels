package com.kaloglu.duels.mobileui.base.mvp

import android.os.Bundle
import android.view.View
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import javax.inject.Inject

abstract class BaseMvpFragment<M, P : BasePresenter<M, BaseView<M>>> : BaseFragment(), BaseView<M> {

    @Inject
    lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        onPresenterAttached()
    }

    override fun onDestroyView() {
        onPresenterDetached()
        presenter.detachView()
        super.onDestroyView()
    }

    // Override this on child fragments if needed.
    protected open fun onPresenterAttached() = Unit

    // Override this on child fragments if needed.
    protected open fun onPresenterDetached() = Unit
}
