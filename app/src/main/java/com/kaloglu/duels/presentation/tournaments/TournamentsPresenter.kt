package com.kaloglu.duels.presentation.tournaments

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import javax.inject.Inject

class TournamentsPresenter @Inject constructor(
        private val localStorage: LocalStorage,
        override val activityNavigator: ActivityNavigator
) : BaseAbstractPresenter<Any, TournamentsContract.View<Any>>(), TournamentsContract.Presenter {

    override fun getNextActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(): OnCompleteListener<Void> =
            OnCompleteListener {
                localStorage.clearSample()
                activityNavigator
                        .toSplashScreen()
                        .finishThis()
                        .navigate()
            }
}
