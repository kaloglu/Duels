package com.kaloglu.duels.data.remote.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kaloglu.duels.BuildConfig
import com.kaloglu.duels.data.remote.NetworkProvider
import com.kaloglu.duels.utils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider(isDebug: Boolean) : NetworkProvider() {

    companion object {
        const val CONNECT_TIME_OUT = 120L
        const val READ_TIME_OUT = 120L
    }

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .client(makeOkHttpClient(isDebug))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    private fun makeOkHttpClient(isDebug: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor(isDebug))
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    override fun <S> create(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }


}
