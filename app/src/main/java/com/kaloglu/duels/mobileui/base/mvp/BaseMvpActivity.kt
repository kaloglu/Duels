package com.kaloglu.duels.mobileui.base.mvp

import android.os.Bundle
import com.kaloglu.duels.domain.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.domain.interfaces.base.mvp.BaseView
import com.kaloglu.duels.mobileui.base.BaseActivity
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        onPresenterAttached()
    }

    override fun onDestroy() {
        onPresenterDetached()
        presenter.detachView()
        super.onDestroy()
    }

    // Override this on child activities if needed.
    protected open fun onPresenterAttached() = Unit

    // Override this on child activities if needed.
    protected open fun onPresenterDetached() = Unit
}
