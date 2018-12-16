/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaloglu.duels.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.kaloglu.duels.api.ApiEmptyResponse
import com.kaloglu.duels.api.ApiErrorResponse
import com.kaloglu.duels.api.ApiResponse
import com.kaloglu.duels.api.ApiSuccessResponse
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import com.kaloglu.duels.viewobjects.Resource
import com.kaloglu.duels.viewobjects.Status
import java.util.*

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType, ParameterType>
@MainThread constructor(open val executorFactory: ExecutorFactory) {

    abstract var param: Param<ParameterType?>

    private val resultMerger = MediatorLiveData<Resource<ResultType>>()

    protected val result
        get() = resultMerger as LiveData<Resource<ResultType>>

    internal fun call() {
        resultMerger.value = Resource.loading(null)
        val cachedSource = loadFromDb()
        resultMerger.addSource(cachedSource) { data ->
            resultMerger.removeSource(cachedSource)
            when {
                shouldFetch(data) -> {
                    val remoteSource = createCall()
                    fetchFromNetwork(remoteSource, cachedSource)
                }
                else -> addSuccessSourceWithCache(cachedSource)
            }
        }
    }

    @MainThread
    protected fun setValue(newValue: Resource<ResultType>) {
        if (resultMerger.value != newValue) {
            resultMerger.value = newValue
        }
    }

    // we re-attach cachedSource as a new source, it will dispatch its latest value quickly
    private fun fetchFromNetwork(remoteSource: LiveData<ApiResponse<RequestType>>, cachedSource: LiveData<ResultType>) {
        resultMerger.addSource(cachedSource) { newData ->
            setValue(Resource.loading(newData))
        }
        resultMerger.addSource(remoteSource) { response ->
            resultMerger.removeSource(remoteSource)
            resultMerger.removeSource(cachedSource)
            when (response) {
                is ApiSuccessResponse -> addSuccessSourceFetchedCache(response)
                is ApiEmptyResponse -> addSuccessSourceWithCache(cachedSource)
                is ApiErrorResponse -> addErrorSource(cachedSource, response)
            }
        }
    }

    private fun addErrorSource(dbSource: LiveData<ResultType>, response: ApiErrorResponse<RequestType>) {
        onFetchFailed()
        resultMerger.addSource(dbSource) { newData ->
            setValue(Resource.error(response.errorMessage, newData))
        }
    }

    private fun addSuccessSourceWithCache(cachedResource: LiveData<ResultType>) {
        executorFactory.mainThread().execute {
            // reload from disk whatever we had
            resultMerger.addSource(cachedResource) { cachedData ->
                setValue(Resource.success(cachedData))
            }
        }
    }

     fun addSuccessSourceFetchedCache(response: ApiSuccessResponse<RequestType>) {
        executorFactory.diskIO().execute {
            saveCallResult(processResponse(response))
            executorFactory.mainThread().execute {
                // we specially request a new live data,
                // otherwise we will get immediately last cached value,
                // which may not be updated with latest results received from network.
                resultMerger.addSource(loadFromDb()) { fetchedData ->
                    setValue(Resource.success(fetchedData))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected fun getMaxRefreshTime(minDifference: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.add(Calendar.MINUTE, -1 * minDifference)
        return cal.time
    }


    @Suppress("UNCHECKED_CAST")
    fun <T> observe(view: BaseView<T>): NetworkBoundResource<ResultType, RequestType, ParameterType> {
        result.observe(view, android.arch.lifecycle.Observer {
            when (it?.status) {
                Status.LOADING -> view.onLoading()
                Status.SUCCESS -> view.onSuccess(it.data as T)
                Status.ERROR -> view.onError(it.message, it.data as T)
                null -> TODO("should define ${it?.status}")
            }
        })

        return this
    }

    open fun setParam(value: ParameterType): NetworkBoundResource<ResultType, RequestType, ParameterType> {
        param = Param(value)
        return this
    }

    data class Param<ParameterType>(var value: ParameterType)
}

