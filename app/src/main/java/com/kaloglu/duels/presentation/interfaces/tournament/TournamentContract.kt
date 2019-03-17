package com.kaloglu.duels.presentation.interfaces.tournament

import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveListView

interface TournamentContract {

    interface View : FormContract.FormView<Tournament> {
        fun getName(): String
    }

    interface Presenter : FormContract.FormPresenter<Tournament, View> {
        //no need on Submit action
        override fun onSubmitForm() = Unit

    }

    interface ListView : ResponseLiveListView<Tournament>, UIStateManager.UIStatesView {
        fun onClickView(item: Tournament, view: android.view.View)
        fun onClickItem(item: Tournament)
    }

    interface ListPresenter : MvpPresenter<ListView>, UIStateManager.UIStatesPresenter {
        fun observeTournamentList()
        fun openTournament(model: Tournament)
        fun removeTournament(item: Tournament)
    }
}
