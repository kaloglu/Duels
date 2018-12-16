package com.kaloglu.duels.mobileui.base.mvp

import android.os.Bundle
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import javax.inject.Inject

//class GenericBar<S : GenericInterface<out FooInterface>> {
//    var s: S? = null
//}
abstract class BaseMvpActivity<M, P : BasePresenter<M, BaseView<M>>> : BaseActivity(), BaseView<M> {

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

    //TODO: override for initial if you need
    override val containedFragment: BaseFragment? = null

    override val baseFrameLayoutId: Int
    get() = TODO("if use a contained")

    // Override this on child activities if needed.
    protected open fun onPresenterAttached() = Unit

    // Override this on child activities if needed.
    protected open fun onPresenterDetached() = Unit
}
