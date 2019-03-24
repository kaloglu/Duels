package com.kaloglu.duels.injection.module

import com.kaloglu.duels.injection.module.main.MainModule
import com.kaloglu.duels.injection.module.splash.SplashModule
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.main.MainActivity
import com.kaloglu.duels.mobileui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributesSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity(): MainActivity

}
