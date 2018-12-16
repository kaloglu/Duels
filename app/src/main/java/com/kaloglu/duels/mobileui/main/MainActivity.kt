package com.kaloglu.duels.mobileui.main

import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import com.firebase.ui.auth.AuthUI
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseMvpActivity<Any, MainContract.Presenter>(), MainContract.View<Any> {

    override fun onLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(errorMessage: String?, data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val contentResourceId = R.layout.activity_main

    override val baseFrameLayoutId = R.id.activity_fragment_container

    //TODO: add a fragment for initial if you need
    override val containedFragment: BaseFragment? = null

    override fun initUserInterface() {
        setSupportActionBar(toolbar)
        toolbar.title = title

        buttonSignOut.setOnClickListener {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(presenter.signOut())
        }

    }

    override fun setTitle(@StringRes resId: Int) = setTitle(getString(resId))

    internal fun setTitle(string: String) {
        supportActionBar?.title = string
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

}
