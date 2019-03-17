package com.kaloglu.duels.mobileui.base.mvp

import android.os.Bundle
import android.view.View
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView
import javax.inject.Inject

abstract class BaseMvpFragment<P : MvpPresenter<MvpView>> : BaseFragment(), MvpView {
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

    override fun refresh() = Unit
    override fun enterAnimation() = Unit
    override fun exitAnimation() = Unit

    override fun showSnackbar(messageId: Int) = Unit
    override fun showSnackbar(message: String) = Unit

    // Override this on child fragments if needed.
    protected open fun onPresenterAttached() = Unit

    // Override this on child fragments if needed.
    protected open fun onPresenterDetached() = Unit
}
