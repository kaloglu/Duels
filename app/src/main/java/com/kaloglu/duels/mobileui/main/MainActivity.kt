package com.kaloglu.duels.mobileui.main

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.animation.OvershootInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.aurelhubert.ahbottomnavigation.notification.AHNotification
import com.firebase.ui.auth.AuthUI
import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.main.ViewPagerAdapter
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.mobileui.demo.DemoFragment
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : BaseMvpActivity<Any, MainContract.Presenter>(), MainContract.View<Any> {

    private lateinit var adapter: ViewPagerAdapter
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
        buttonSignOut.setOnClickListener {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(presenter.signOut())
        }

        adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.offscreenPageLimit = 3
        view_pager.adapter = adapter

        currentFragment = adapter.currentFragment

        createBottomNavigation()

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

            if (position == 1) {

                fab.show()
                fab.alpha = 0f
                fab.scaleX = 0f
                fab.scaleY = 0f
                fab.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(300)
                        .setInterpolator(OvershootInterpolator())
                        .setListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator) = Unit

                            override fun onAnimationEnd(animation: Animator) =
                                    fab.animate()
                                            .setInterpolator(LinearOutSlowInInterpolator())
                                            .start()

                            override fun onAnimationCancel(animation: Animator) = Unit

                            override fun onAnimationRepeat(animation: Animator) = Unit
                        })
                        .start()

            } else if (fab.isShown) {
                fab.animate()
                        .alpha(0f)
                        .scaleX(0f)
                        .scaleY(0f)
                        .setDuration(300)
                        .setInterpolator(LinearOutSlowInInterpolator())
                        .setListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator) = Unit

                            override fun onAnimationEnd(animation: Animator) = fab.hide()

                            override fun onAnimationCancel(animation: Animator) = fab.hide()

                            override fun onAnimationRepeat(animation: Animator) = Unit
                        })
                        .start()
            }

            true
        })
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
