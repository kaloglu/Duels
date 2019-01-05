package com.kaloglu.duels.mobileui.demo

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_demo.*
import javax.inject.Inject

class DemoFragment @Inject constructor() : BaseFragment() {
    override val resourceLayoutId = R.layout.fragment_demo

    override fun initUserInterface(rootView: View) {
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
        if (arguments!!.getInt("index", 0) > 0 && recycler_view != null) {
            recycler_view.smoothScrollToPosition(0)
        }
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
