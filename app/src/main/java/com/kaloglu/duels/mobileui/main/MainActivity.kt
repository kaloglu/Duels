package com.kaloglu.duels.mobileui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.notification.AHNotification
import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.main.ViewPagerAdapter
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import com.kaloglu.duels.utils.hide
import com.kaloglu.duels.utils.show
import com.kaloglu.duels.utils.withAnimation
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var adapter: ViewPagerAdapter

    override val contentResourceId = R.layout.activity_main

    override val baseFrameLayoutId = -1

    override val snackbarLayoutId = R.id.bottom_navigation

    override fun initUserInterface() {
        view_pager.offscreenPageLimit = 3
        view_pager.adapter = adapter

        currentFragment = adapter.currentFragment

        createBottomNavigation()

        fab.setOnClickListener {
            presenter.newTournament()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount < 1)
            presenter.onClearFragmentContainer()
    }

    override fun showContentContainer(show: Boolean) {
        content_container.withAnimation(
                alpha = (if (show) 1 else 0).toFloat(),
                onStart = { if (show) it.show() },
                onEnd = { if (!show) it.hide() }
        )
    }

    private fun createBottomNavigation() {
        val tabColors = applicationContext.resources.getIntArray(R.array.tab_colors)
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(bottom_navigation, tabColors)

        bottom_navigation.defaultBackgroundColor = Color.WHITE
        bottom_navigation.accentColor = ContextCompat.getColor(this, R.color.colorAccent)
//  Enables color Reveal effect
        bottom_navigation.isColored = true
// Colors for selected (active) and non-selected items (in color reveal mode).
//        bottom_navigation.setColoredModeColors(Color.WHITE, ContextCompat.getColor(this, R.color.bottomtab_item_resting))
        bottom_navigation.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE_FORCE
        bottom_navigation.isTranslucentNavigationEnabled = true
        bottom_navigation.isBehaviorTranslationEnabled = true

        bottom_navigation.manageFloatingActionButtonBehavior(fab)

        bottom_navigation.setOnTabSelectedListener(AHBottomNavigation.OnTabSelectedListener { position, wasSelected ->
            if (currentFragment == null) {
                currentFragment = adapter.currentFragment
            }

            if (position == badgeVisible)
                bottom_navigation.setNotification(AHNotification(), badgeVisible)
//            else {
//                //Dummy
//                Handler().postDelayed({
//                    if (!wasSelected)
//                        addBadge(Random.nextInt(2), (Random.nextInt(9) + 1).toString())
//
//                }, 2000)
//            }

            if (wasSelected) {
                currentFragment!!.refresh()
                return@OnTabSelectedListener true
            }

            currentFragment?.exitAnimation()

            view_pager.setCurrentItem(position, false)

            if (currentFragment == null) {
                return@OnTabSelectedListener true
            }

//            addBadge(Random.nextInt(2), (Random.nextInt(9) + 1).toString())
            currentFragment = adapter.currentFragment
            currentFragment!!.enterAnimation()

            manageFabAnimation(position)

            true
        })
    }

    private fun manageFabAnimation(position: Int) {
        fab.withAnimation(
                predicate = (position == 0),
                alpha = 1f,
                scale = 1f,
                interpolator = OvershootInterpolator(),
                onStart = { it.show() }
        )

        fab.withAnimation(
                predicate = (position != 0 && fab.isShown),
                alpha = 0f,
                scale = 0f,
                onEnd = { it.hide() },
                onCancel = { it.hide() }
        )
    }

    private fun addBadge(witch: Int, badgeCount: String) {
        Handler().postDelayed({
            val notification = AHNotification.Builder()
                    .setText(badgeCount)
                    .setBackgroundColor(Color.RED)
                    .setTextColor(Color.WHITE)
                    .build()
            // Adding notification to last item.
            bottom_navigation.setNotification(notification, witch)
            badgeVisible = witch
        }, 1000)
    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
