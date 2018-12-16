package com.kaloglu.duels.api.service

import android.arch.lifecycle.LiveData
import com.kaloglu.duels.api.ApiResponse
import com.kaloglu.duels.data.remote.model.SampleModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SampleServiceRetrofit {
    val mutableResult: Any

    @GET("/getSample")
    fun getSample(@Query("paramName") paramName: String): LiveData<ApiResponse<SampleModel>>

    @Suppress("UNCHECKED_CAST")
    fun <T> asLiveData(): T {
        return mutableResult as T
    }
}
