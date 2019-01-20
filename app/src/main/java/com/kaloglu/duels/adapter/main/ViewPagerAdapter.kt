package com.kaloglu.duels.adapter.main

import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.demo.DemoFragment
import java.util.*
import javax.inject.Inject

/**
 *
 */
class ViewPagerAdapter @Inject constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = ArrayList<DemoFragment>()
    /**
     * Get the current fragment
     */
    var currentFragment: DemoFragment? = null

    init {

        fragments.clear()
        fragments.add(DemoFragment.newInstance(0))
        fragments.add(DemoFragment.newInstance(1))
        fragments.add(DemoFragment.newInstance(2))
    }

    override fun getItem(position: Int): BaseFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as DemoFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}
