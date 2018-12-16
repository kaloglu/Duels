package com.kaloglu.duels.presentation.interfaces.main

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView

interface MainContract {

    interface View<M> : BaseView<M>

    interface Presenter : BasePresenter<Any, View<Any>> {
        fun signOut(): OnCompleteListener<Void>
    }
}
