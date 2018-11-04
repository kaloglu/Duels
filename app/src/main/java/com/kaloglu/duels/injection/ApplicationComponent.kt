package com.kaloglu.duels.injection

import android.app.Application
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.DuelsApp
import com.kaloglu.duels.injection.module.ActivityBindingModule
import com.kaloglu.duels.injection.module.ApplicationModule
import com.kaloglu.duels.injection.module.DataModule
import com.kaloglu.duels.injection.module.PreferencesModule
import com.kaloglu.duels.injection.module.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    PreferencesModule::class,
    ActivityBindingModule::class,
    DataModule::class,
    RemoteModule::class
])
interface ApplicationComponent : AndroidInjector<DuelsApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(githubApp: DuelsApp)
}
