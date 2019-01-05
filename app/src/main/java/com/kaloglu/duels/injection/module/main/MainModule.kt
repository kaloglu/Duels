package com.kaloglu.duels.injection.module.main

import com.kaloglu.duels.injection.module.ActivityModule
import com.kaloglu.duels.injection.module.demo.DemoModule
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.demo.DemoFragment
import com.kaloglu.duels.mobileui.main.MainActivity
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import com.kaloglu.duels.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun presenter(activityNavigator: ActivityNavigator): MainContract.Presenter =
                MainPresenter(activityNavigator)

    }

    @Binds
    @PerActivity
    abstract fun main(activity: MainActivity): BaseActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [DemoModule::class])
    abstract fun contributesDemoFragment(): DemoFragment

}
