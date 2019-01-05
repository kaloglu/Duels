package com.kaloglu.duels.presentation.interfaces.main

import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView

interface MainContract {

    interface View<M> : BaseView<M>

    interface Presenter : BasePresenter<Any, View<Any>>
}
