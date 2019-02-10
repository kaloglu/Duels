package com.kaloglu.duels.mobileui.base.mvp

import android.os.Bundle
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityPresenter
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityView
import javax.inject.Inject

abstract class BaseMvpActivity<P : ActivityPresenter<ActivityView>> : BaseActivity(), ActivityView {

    //TODO: override for initial if you need
    override val containedFragment: BaseFragment? = null

    protected var badgeVisible: Int = -1

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        onPresenterAttached()
        savedInstanceState ?: let {
            if (containedFragment != null)
                presenter.showFragment(containedFragment!!)
        }
    }

    override fun onDestroy() {
        onPresenterDetached()
        presenter.detachView()
        super.onDestroy()
    }

    //TODO: override for pager current fragment if you need
    override var currentFragment: BaseFragment? = null

    override val baseFrameLayoutId: Int
        get() = TODO("if use a contained")

    // Override this on child activities if needed.
    protected open fun onPresenterAttached() = Unit

    // Override this on child activities if needed.
    protected open fun onPresenterDetached() = Unit
}
