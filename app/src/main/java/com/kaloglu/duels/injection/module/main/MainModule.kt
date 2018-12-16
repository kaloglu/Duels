package com.kaloglu.duels.injection.module.main

import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.injection.module.ActivityModule
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.main.MainActivity
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import com.kaloglu.duels.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun presenter(
                localStorage: LocalStorage,
                activityNavigator: ActivityNavigator
        ): MainContract.Presenter =
                MainPresenter(localStorage, activityNavigator)

    }

    @Binds
    @PerActivity
    abstract fun main(activity: MainActivity): BaseActivity

}
