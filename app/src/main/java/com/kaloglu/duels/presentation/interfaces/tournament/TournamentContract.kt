package com.kaloglu.duels.presentation.interfaces.tournament

import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveListView

interface TournamentContract {

    interface View : FormContract.FormView<Tournament> {
        fun getName(): String
    }

    interface Presenter : FormContract.FormPresenter<Tournament, View> {
        //no need on Submit action
        override fun onSubmitForm() = Unit

    }

    interface ListView : ResponseLiveListView<Tournament>, UIStateManager.UIStatesView

    interface ListPresenter : BasePresenter<ListView>, UIStateManager.UIStatesPresenter {
        fun observeTournamentList()
    }
}
