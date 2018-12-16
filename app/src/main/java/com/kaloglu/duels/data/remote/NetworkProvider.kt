package com.kaloglu.duels.data.remote

abstract class NetworkProvider {
    abstract fun <S> create(serviceClass: Class<S>): S
}
