package com.kaloglu.duels.firebase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import com.kaloglu.duels.api.RemoteResponse
import com.kaloglu.duels.api.RemoteSuccessResponse
import com.kaloglu.duels.data.remote.model.SampleModel

typealias M = SampleModel

class SampleServiceFirebase /*( private val sampleInjection: SampleInjection )*/ {
    private val mutableResult: MutableLiveData<RemoteResponse<M>> = MutableLiveData()

    @Suppress("UNCHECKED_CAST")
    fun <T> asLiveData(): T {
        return mutableResult as T
    }

    fun getSampleModel(): LiveData<RemoteResponse<M>> {
        setValue(RemoteSuccessResponse(SampleModel(), ""))
        return mutableResult
    }

    @MainThread
    private fun setValue(newValue: RemoteResponse<M>) {
        if (mutableResult.value != newValue) {
            mutableResult.value = newValue
        }
    }
}
