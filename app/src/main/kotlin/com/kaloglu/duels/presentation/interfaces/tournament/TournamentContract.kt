package com.kaloglu.duels.presentation.interfaces.tournament

import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListView

interface TournamentContract {

    interface View : FormContract.FormView {
        fun getTournamentName(): String
    }

    interface Presenter : FormContract.FormPresenter<Tournament, View>

    interface ListView : MvpListView<Tournament>

    interface ListPresenter : MvpListPresenter<Tournament, ListView>

}
