package com.kaloglu.duels.mobileui.main

import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.main.ViewPagerAdapter
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.mobileui.tournament.TournamentListFragment
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var adapter: ViewPagerAdapter

    override val contentResourceId = R.layout.activity_main

    override val containedFragment = TournamentListFragment()

    override val snackbarLayoutId = R.id.fragment_container

    override fun initUserInterface() = Unit

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount < 1)
            presenter.onClearFragmentContainer()
    }

    override fun showContentContainer(show: Boolean) = Unit

}
