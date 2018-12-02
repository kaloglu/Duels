package com.kaloglu.duels.injection.module.splash

import com.kaloglu.duels.domain.interfaces.splash.SplashContract
import com.kaloglu.duels.domain.splash.SplashPresenter
import com.kaloglu.duels.injection.module.ActivityModule
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.splash.SplashActivity
import com.kaloglu.duels.navigation.ActivityNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityModule::class])
abstract class SplashModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        fun presenter(activityNavigator: ActivityNavigator): SplashContract.Presenter =
                SplashPresenter(activityNavigator)

    }

    @Binds
    @PerActivity
    abstract fun main(activity: SplashActivity): BaseActivity

}
