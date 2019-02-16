package com.kaloglu.duels.injection.module.data

import com.kaloglu.duels.domain.ExecutorFactory
import com.kaloglu.duels.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides


@Module
abstract class DataModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerApplication
        fun provideExecutorFactory() = ExecutorFactory()
    }


}
