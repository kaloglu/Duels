package com.kaloglu.duels.adapter.main

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.demo.DemoFragment
import com.kaloglu.duels.mobileui.tournaments.TournamentsFragment
import java.util.*
import javax.inject.Inject

/**
 *
 */
class ViewPagerAdapter @Inject constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = ArrayList<BaseFragment>()
    /**
     * Get the current fragment
     */
    var currentFragment: BaseFragment? = null

    init {

        fragments.clear()
        fragments.add(TournamentsFragment.newInstance())
    }

    override fun getItem(position: Int): BaseFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as BaseFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}
