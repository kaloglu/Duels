package com.kaloglu.duels.injection.module.data.remote

import android.app.Application
import com.kaloglu.duels.data.remote.NetworkProvider
import com.kaloglu.duels.data.remote.firestore.FirebaseProvider
import com.kaloglu.duels.firebase.SampleServiceFirebase
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides


@Module
abstract class RemoteModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun firebaseSampleService(provider: NetworkProvider) =
                provider.create(SampleServiceFirebase::class.java)

        @JvmStatic
        @PerApplication
        @Provides
        fun networkProvider(application: Application) = FirebaseProvider(application)

    }

}
