package com.kaloglu.duels.data.remote

import android.app.Application
import com.kaloglu.duels.BuildConfig
import com.kaloglu.duels.data.remote.firestore.FirebaseProvider
import com.kaloglu.duels.data.remote.retrofit.RetrofitProvider
import javax.inject.Inject

class ProviderFactory @Inject constructor() {
    internal fun getProvider(application: Application, providerName: String) =
            when (providerName) {
                "retrofit" -> RetrofitProvider(BuildConfig.DEBUG)
                else -> FirebaseProvider(application)
            }
}
