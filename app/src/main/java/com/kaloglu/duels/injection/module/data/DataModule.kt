package com.kaloglu.duels.injection.module.data

import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.data.cache.sample.SampleDao
import com.kaloglu.duels.data.remote.model.SampleModel
import com.kaloglu.duels.data.repository.sample.SampleRepository
import com.kaloglu.duels.domain.ExecutorFactory
import com.kaloglu.duels.domain.NetworkBoundResource
import com.kaloglu.duels.firebase.SampleServiceFirebase
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.presentation.interfaces.SampleMapper
import com.kaloglu.duels.viewobjects.CachedSample
import dagger.Module
import dagger.Provides


@Module
abstract class DataModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerApplication
        fun provideSampleRepository(
                localStorage: LocalStorage,
                sampleServiceFirebase: SampleServiceFirebase,
                sampleMapper: SampleMapper,
                sampleDao: SampleDao,
                executor: ExecutorFactory
        ): NetworkBoundResource<CachedSample, SampleModel, String> =
                SampleRepository(localStorage, sampleServiceFirebase, sampleMapper, sampleDao, executor)

        @JvmStatic
        @Provides
        @PerApplication
        fun provideExecutorFactory() = ExecutorFactory()
    }


}
