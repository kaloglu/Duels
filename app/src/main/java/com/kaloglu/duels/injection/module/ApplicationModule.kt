package com.kaloglu.duels.injection.module

import android.content.Context
import com.kaloglu.duels.injection.qualifier.ApplicationContext
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.DuelsApp
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @ApplicationContext
    @PerApplication
    @Binds
    abstract fun bindApplication(application: DuelsApp): Context


}
