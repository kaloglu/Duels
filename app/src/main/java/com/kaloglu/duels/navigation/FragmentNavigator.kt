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

    fun showFragment(fragment: BaseFragment) =
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true) // setAllowOptimization before 26.1.0
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
