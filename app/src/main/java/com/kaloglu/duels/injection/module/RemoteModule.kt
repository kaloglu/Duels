package com.kaloglu.duels.injection.module

import com.kaloglu.duels.BuildConfig
import com.kaloglu.duels.api.DuelsService
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun provideDuelsService(): DuelsService {
            return Retrofit.Builder()
                    .baseUrl(BuildConfig.END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
                    .create(DuelsService::class.java)
        }

    }

}
