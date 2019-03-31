package com.kaloglu.duels.mobileui.base.mvp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView
import javax.inject.Inject

abstract class BaseMvpFragment<V : MvpView, P : MvpPresenter<V>> : BaseFragment(), MvpView {
    @Inject
    lateinit var presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun getMvpActivity() = activity as MvpView

    override fun handleSignInResult(data: Intent?, resultCode: Int) =
            getMvpActivity().handleSignInResult(data, resultCode)

    override fun showSnackbar(messageId: Int) = getMvpActivity().showSnackbar(messageId)

    override fun showSnackbar(message: String) = getMvpActivity().showSnackbar(message)

    override fun refresh() = Unit

    override fun enterAnimation() = Unit

    override fun exitAnimation() = Unit

    override fun onPresenterAttached() = presenter.checkAuth()

    override fun onPresenterDetached() = Unit

}