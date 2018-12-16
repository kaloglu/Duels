package com.kaloglu.duels.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.kaloglu.duels.viewobjects.CachedSample

class LocalStorage(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        const val SAMPLE_PARAMETER_KEY = "email"
    }

    inline operator fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
        return when {
            T::class == Boolean::class -> this.getBoolean(key, defaultValue as Boolean) as T
            T::class == Float::class -> this.getFloat(key, defaultValue as Float) as T
            T::class == Int::class -> this.getInt(key, defaultValue as Int) as T
            T::class == Long::class -> this.getLong(key, defaultValue as Long) as T
            T::class == String::class -> this.getString(key, defaultValue as String) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }

    }

    inline operator fun <reified T> SharedPreferences.set(key: String, value: T) {
        edit().apply {
            when (T::class) {
                Boolean::class -> putBoolean(key, value as Boolean)
                Float::class -> putFloat(key, value as Float)
                Int::class -> putInt(key, value as Int)
                Long::class -> putLong(key, value as Long)
                String::class -> putString(key, value as String)
                else -> throw UnsupportedOperationException("Not yet implemented")
            }

            apply()
        }
    }

    fun getSample() = preferences[SAMPLE_PARAMETER_KEY, ""]

    fun setSample(cachedSample: CachedSample?) =
            when {
                cachedSample?.sampleParameter.isNullOrEmpty().not() ->
                    preferences[SAMPLE_PARAMETER_KEY] = cachedSample?.sampleParameter
                else -> preferences[SAMPLE_PARAMETER_KEY] = ""
            }

    fun cleaSample() = setSample(null)

}
