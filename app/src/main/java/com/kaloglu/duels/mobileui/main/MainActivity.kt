package com.kaloglu.duels.mobileui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.animation.OvershootInterpolator
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.notification.AHNotification
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.main.ViewPagerAdapter
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.mobileui.demo.DemoFragment
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import com.kaloglu.duels.utils.with
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.random.Random


class MainActivity : BaseMvpActivity<Any, MainContract.Presenter>(), MainContract.View<Any> {

    @Inject
    lateinit var adapter: ViewPagerAdapter

    private var currentFragment: DemoFragment? = null

    override fun onLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(errorMessage: String?, data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val contentResourceId = R.layout.activity_main

    override val baseFrameLayoutId = -1

    override val snackbarLayoutId = R.id.bottom_navigation

    //TODO: add a fragment for initial if you need
    override val containedFragment: BaseFragment? = null

    override fun initUserInterface() {
//        setSupportActionBar(toolbar)
//        toolbar.title = title
//
        view_pager.offscreenPageLimit = 3
        view_pager.adapter = adapter

        currentFragment = adapter.currentFragment

        createBottomNavigation()

        fab.setOnClickListener {
            showSnackbar("snackbar test")
        }

    }

    private fun createBottomNavigation() {
        val tabColors = applicationContext.resources.getIntArray(R.array.tab_colors)
        val navigationAdapter = AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu)
        navigationAdapter.setupWithBottomNavigation(bottom_navigation, tabColors)

        bottom_navigation.defaultBackgroundColor = Color.WHITE
        bottom_navigation.accentColor = fetchColor(R.color.colorAccent)
//  Enables color Reveal effect
        bottom_navigation.isColored = true
// Colors for selected (active) and non-selected items (in color reveal mode).
//        bottom_navigation.setColoredModeColors(Color.WHITE, fetchColor(R.color.bottomtab_item_resting))
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
            else {
                //Dummy
                Handler().postDelayed({
                    if (!wasSelected)
                        addBadge(Random.nextInt(2), (Random.nextInt(9) + 1).toString())

                }, 2000)
            }
            if (wasSelected) {
                currentFragment!!.refresh()
                return@OnTabSelectedListener true
            }

            currentFragment?.willBeHidden()

            view_pager.setCurrentItem(position, false)

            if (currentFragment == null) {
                return@OnTabSelectedListener true
            }
            addBadge(Random.nextInt(2), (Random.nextInt(9) + 1).toString())
            currentFragment = adapter.currentFragment
            currentFragment!!.willBeDisplayed()

            manageFabAnimation(position)

            true
        })
    }

    private fun manageFabAnimation(position: Int) {
        fab.with(
                predicate = (position == 0),
                value = 1f,
                interpolator = OvershootInterpolator(),
                onStart = FloatingActionButton::show,
                onEnd = { it.with() }
        )

        fab.with(
                predicate = (position != 0 && fab.isShown),
                value = 0f,
                onEnd = FloatingActionButton::hide,
                onCancel = FloatingActionButton::hide
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
