package com.kaloglu.duels.injection

import android.app.Application
import com.kaloglu.duels.DuelsApp
import com.kaloglu.duels.injection.firestore.FirestoreModule
import com.kaloglu.duels.injection.module.ActivityBindingModule
import com.kaloglu.duels.injection.module.ApplicationModule
import com.kaloglu.duels.injection.module.ContextModule
import com.kaloglu.duels.injection.module.data.DataModule
import com.kaloglu.duels.injection.module.data.cache.CacheModule
import com.kaloglu.duels.injection.module.data.remote.RemoteModule
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ContextModule::class,
    ActivityBindingModule::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class,
    FirestoreModule::class
])
interface ApplicationComponent : AndroidInjector<DuelsApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(duelsApp: DuelsApp)

}
