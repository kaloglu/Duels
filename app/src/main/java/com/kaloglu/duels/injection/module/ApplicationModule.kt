package com.kaloglu.duels.injection.module

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.injection.qualifier.ApplicationContext
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class ApplicationModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerApplication
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

    }

    @ApplicationContext
    @PerApplication
    @Binds
    abstract fun bindApplication(application: Application): Context

}
