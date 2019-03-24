package com.kaloglu.duels.presentation.interfaces.tournament

import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveListView

typealias Model = Tournament

interface TournamentContract {

    interface View : FormContract.FormView {
        fun getName(): String
    }

    interface Presenter : FormContract.FormPresenter<Model, View> {
        //no need on Submit action
        override fun onSubmitForm() = Unit

    }

    interface ListView : ResponseLiveListView<Model>, UIStateManager.UIStatesView {
        fun onClickView(model: Model, view: android.view.View)
        fun onClickItem(model: Model)
    }

    interface ListPresenter : MvpPresenter<ListView>, UIStateManager.UIStatesPresenter {
        fun observe()
        fun openDetail(model: Model)
        fun remove(model: Model)
    }

}
