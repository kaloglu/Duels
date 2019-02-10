package com.kaloglu.duels.utils

/**
 * Created by kaloglu on 6.01.2019.
 */

internal val String.Companion.empty
    get() = ""

fun <T : Any> Class<T>.checkInjection(any: T?) =
    checkNotNull(any) {
        val firstChar = simpleName.first()
        val firstCharLowerCase = simpleName.replaceFirst(firstChar, firstChar.toLowerCase())
        "you should add \"$firstCharLowerCase: $simpleName\" to providing Presenter method at Module"
    }

