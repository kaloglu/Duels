package com.kaloglu.duels.presentation.interfaces.tournaments

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView

interface TournamentsContract {

    interface View : BaseView<List<Tournament>>, UIStateManager.UIStatesView

    interface Presenter : BasePresenter<List<Tournament>, View>, UIStateManager.UIStatesPresenter {
        fun signOut(): OnCompleteListener<Void>
        fun loadTournaments()
    }
}
