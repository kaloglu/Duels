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

class DemoFragment : BaseMvpFragment<Any?, DemoContract.View, DemoContract.Presenter>() {

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

}
