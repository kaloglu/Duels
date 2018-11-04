package com.kaloglu.duels.mobileui.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    //TODO: will be add @inject FragmentNavigator

    /**
     * Method to get activity's UI content frame layout resource id.
     *
     * @return The activity's frame layout resource id
     */
    protected abstract val contentResourceId: Int

    /**
     * Method to get activity's UI content layout resource id.
     * if overwritten [baseFrameLayoutId][.baseFrameLayoutId] must also be implemented.
     *
     * @return The activity's content's resource id
     */
    protected abstract val baseFrameLayoutId: Int

    /**
     * Get initial fragment instance.
     *
     * @return Fragment
     */
    protected abstract val containedFragment: BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()

        initUserInterface()

        showInitialFragment(savedInstanceState)
    }

    private fun showInitialFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) return

        //TODO: will be replaced to fragmentNavigator.showFragment(containedFragment)
        containedFragment?.run {
            supportFragmentManager.beginTransaction()
                    .add(baseFrameLayoutId, this, fragmentTag)
                    .commit()
        }
    }

    /**
     * Initialize UI content elements.
     */
    protected abstract fun initUserInterface()

    protected open fun setContentView() = setContentView(contentResourceId)
}
