package com.kaloglu.duels.firebase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
