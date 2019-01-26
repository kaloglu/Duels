package com.kaloglu.duels.presentation.interfaces.tournaments

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView

interface TournamentsContract {

    interface View<M> : BaseView<M>, UIStateManager.UIStatesView

    interface Presenter : BasePresenter<Any, View<Any>>, UIStateManager.UIStatesPresenter {
        fun signOut(): OnCompleteListener<Void>
    }
}
