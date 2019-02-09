package com.kaloglu.duels.presentation.interfaces.tournaments

import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView
import com.kaloglu.duels.presentation.interfaces.base.mvp.ResponseLiveDataView

interface TournamentsContract {

    interface View : BaseView, ResponseLiveDataView<List<Tournament>>,UIStateManager.UIStatesView

    interface Presenter : BasePresenter<View>, UIStateManager.UIStatesPresenter {
        fun loadTournaments()
    }
}
