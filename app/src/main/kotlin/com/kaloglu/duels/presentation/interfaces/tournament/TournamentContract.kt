package com.kaloglu.duels.presentation.interfaces.tournament

import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.presentation.interfaces.base.mvp.FormContract
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpListView

typealias Model = Tournament

interface TournamentContract {

    interface View : FormContract.FormView {
        fun getTournamentName(): String
    }

    interface Presenter : FormContract.FormPresenter<Model, View> {
        //no need on Submit action
        override fun onSubmitForm() = Unit

    }

    interface ListView : MvpListView<Model>

    interface ListPresenter : MvpListPresenter<Model, ListView>

}
