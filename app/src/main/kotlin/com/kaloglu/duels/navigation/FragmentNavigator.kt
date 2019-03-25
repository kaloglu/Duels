package com.kaloglu.duels.navigation

import androidx.fragment.app.FragmentManager
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.mobileui.base.BaseFragment

@PerActivity
@PerFragment
abstract class FragmentNavigator constructor(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) {

    interface FragmentCallback {
        fun onClearFragmentContainer()
        fun onFillFragmentContainer()
    }

    private var callback: FragmentCallback? = null

    fun setCallback(callback: FragmentCallback?): FragmentNavigator {
        this.callback = callback
        return this
    }

    fun showFragment(fragment: BaseFragment): FragmentNavigator {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true) // setAllowOptimization before 26.1.0
                .replace(containerId, fragment)
                .addToBackStack(fragment.fragmentTag)
                .commit()

        callback?.onFillFragmentContainer()

        return this
    }


    fun popBackStack(): FragmentNavigator {
        if (fragmentManager.backStackEntryCount == 1)
            callback?.onClearFragmentContainer()

        fragmentManager.popBackStack()

        return this
    }

    fun clearPopStack(): FragmentNavigator {
        while (fragmentManager.backStackEntryCount > 0) {
            popBackStack()
        }
        return this
    }

}
