package com.kaloglu.duels.navigation

import androidx.fragment.app.FragmentManager
import com.kaloglu.duels.mobileui.base.BaseFragment

abstract class FragmentNavigator constructor(private val fragmentManager: FragmentManager) {
    //TODO: could be abstract
    abstract val containerId: Int

    fun showFragment(fragment: BaseFragment) =
            fragmentManager.beginTransaction()
                    .replace(containerId, fragment)
                    .addToBackStack(fragment.fragmentTag)
                    .commit()


    fun popBackStack() = fragmentManager.popBackStack()

    fun clearPopStack() {
        var count = fragmentManager.backStackEntryCount
        while (count > 1) {
            fragmentManager.popBackStack()
            count--
        }
    }

}
