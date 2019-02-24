package com.kaloglu.duels.presentation.interfaces.base.mvp

import com.kaloglu.duels.data.model.BaseModel

interface ResponseLiveListView<T : BaseModel> : BaseView {
    fun onLoading()
    fun onSuccess(data: List<T>)
    fun onEmpty()
    fun onError(errorMessage: String?, data: List<T>?)

}
