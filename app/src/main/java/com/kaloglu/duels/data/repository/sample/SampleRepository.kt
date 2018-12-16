package com.kaloglu.duels.data.repository.sample

import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.data.cache.sample.SampleDao
import com.kaloglu.duels.data.remote.model.SampleModel
import com.kaloglu.duels.domain.ExecutorFactory
import com.kaloglu.duels.domain.NetworkBoundResource
import com.kaloglu.duels.firebase.SampleServiceFirebase
import com.kaloglu.duels.injection.scopes.PerApplication
import com.kaloglu.duels.presentation.interfaces.SampleMapper
import com.kaloglu.duels.viewobjects.CachedSample
import javax.inject.Inject

@PerApplication
class SampleRepository @Inject constructor(
        private val localStorage: LocalStorage,
        private val sampleServiceFirebase: SampleServiceFirebase,
        private val sampleMapper: SampleMapper,
        private val sampleDao: SampleDao,
        override val executorFactory: ExecutorFactory
) : NetworkBoundResource<CachedSample, SampleModel, String>(executorFactory) {

    override var param: Param<String?> = Param("")

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun saveCallResult(sampleModel: SampleModel) {
        val cachedSample = sampleMapper.mapTo(sampleModel)
        localStorage.setSample(cachedSample)
        sampleDao.insert(cachedSample)
    }

    override fun shouldFetch(data: CachedSample?) = data?.dataExpired(getMaxRefreshTime(FRESH_TIMEOUT_IN_MINUTES)) ?: true

    override fun loadFromDb() = sampleDao.findByLogin(localStorage.getSample())

    override fun createCall() = sampleServiceFirebase.getSampleModel()

    companion object {
        private const val FRESH_TIMEOUT_IN_MINUTES = 100
    }
}
