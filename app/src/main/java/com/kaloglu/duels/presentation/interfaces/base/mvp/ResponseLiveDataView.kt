package com.kaloglu.duels.presentation.interfaces.base.mvp

interface ResponseLiveDataView<in T> : BaseView {
    fun onLoading()
    fun onSuccess(data: T)
    fun onError(errorMessage: String?, data: T?)
}
