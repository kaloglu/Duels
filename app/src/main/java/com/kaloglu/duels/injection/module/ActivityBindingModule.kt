package com.kaloglu.duels.injection.module

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.main.MainActivity
import com.kaloglu.duels.injection.module.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ActivityModule::class])
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesRepoListActivity(): MainActivity

}
