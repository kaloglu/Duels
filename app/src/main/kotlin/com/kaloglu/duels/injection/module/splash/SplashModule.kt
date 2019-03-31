package com.kaloglu.duels.injection.module.splash

import com.kaloglu.duels.injection.module.ActivityModule
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.base.BaseActivity
import com.kaloglu.duels.mobileui.splash.SplashActivity
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.splash.SplashContract
import com.kaloglu.duels.presentation.splash.SplashPresenter
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
        fun presenter(genericDependencies: GenericDependencies): SplashContract.Presenter =
                SplashPresenter(genericDependencies)

    }

    @Binds
    @PerActivity
    abstract fun splash(activity: SplashActivity): BaseActivity

}
