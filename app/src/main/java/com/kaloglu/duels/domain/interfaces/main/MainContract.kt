package com.kaloglu.duels.domain.interfaces.main

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.domain.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.domain.interfaces.base.mvp.BaseView

interface MainContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View> {
        fun signOut(): OnCompleteListener<Void>
    }
}
