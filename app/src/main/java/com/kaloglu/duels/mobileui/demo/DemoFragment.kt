package com.kaloglu.duels.mobileui.demo

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.firebase.ui.auth.AuthUI
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.presentation.interfaces.demo.DemoContract
import kotlinx.android.synthetic.main.fragment_demo.*
import javax.inject.Inject

class DemoFragment @Inject constructor() : BaseMvpFragment<Any, DemoContract.Presenter>(), DemoContract.View<Any> {

    override fun showSnackbar(messageId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSnackbar(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(errorMessage: String?, data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val resourceLayoutId = R.layout.fragment_demo

    override fun initUserInterface(rootView: View) {
        buttonSignOut?.setOnClickListener {
            AuthUI.getInstance()
                    .signOut(context!!)
                    .addOnCompleteListener(presenter.signOut())
        }
        if (arguments!!.getInt("index", 0) == 0) {
            buttonSignOut?.visibility = View.VISIBLE
        } else {
            buttonSignOut?.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance(index: Int): DemoFragment {
            val fragment = DemoFragment()
            val b = Bundle()
            b.putInt("index", index)
            fragment.arguments = b
            return fragment
        }
    }

    /**
     * Refresh
     */
    fun refresh() {
    }

    /**
     * Called when a fragment will be displayed
     */
    fun willBeDisplayed() {
        // Do what you want here, for example animate the content
        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        fragment_container.startAnimation(fadeIn)
    }

    /**
     * Called when a fragment will be hidden
     */
    fun willBeHidden() {
        val fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
        fragment_container.startAnimation(fadeOut)
    }

}
