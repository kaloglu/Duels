package com.kaloglu.duels.injection.module

import android.app.Application
import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {
    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun providesLocalStorage(application: Application): LocalStorage =
                LocalStorage(application)
    }
}
