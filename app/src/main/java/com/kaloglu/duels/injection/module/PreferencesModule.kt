package com.kaloglu.duels.injection.module

import android.content.Context
import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.injection.qualifier.ApplicationContext
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @PerApplication
    @Provides
    fun providesSharedDataPreferences(@ApplicationContext context: Context): LocalStorage {
        return LocalStorage(context)
    }
}
