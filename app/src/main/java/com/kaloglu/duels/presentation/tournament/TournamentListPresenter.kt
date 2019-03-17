package com.kaloglu.duels.presentation.tournament

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.mobileui.tournament.TournamentFragment
import com.kaloglu.duels.presentation.base.BasePresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.utils.extensions.observe
import javax.inject.Inject

class TournamentListPresenter @Inject constructor(
        private val repository: TournamentRepository,
        override var uiStateManager: UIStateManager?,
        override val genericDependencies: GenericDependencies
) : BasePresenter<TournamentContract.ListView>(), TournamentContract.ListPresenter {
    private lateinit var isSignedIn: LiveData<Boolean>

    override fun getNextActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(): OnCompleteListener<Void> =
            OnCompleteListener {
                activityNavigator
                        .toSplashScreen()
                        .finishThis()
                        .navigate()
            }


    override fun observeTournamentList() {
        getView().onLoading()
        isSignedIn = object : LiveData<Boolean>() {
            override fun onActive() {
                super.onActive()
                value = FirebaseAuth.getInstance().currentUser != null
            }
        }

        repository.getTournamentList(null).observe(getView())
    }

    override fun getUIState(state: UIStateManager.UIStateType) {
        uiStateManager?.getState(state)
    }

    override fun openTournament(model: Tournament) {
        genericDependencies
                .fragmentNavigator
                .showFragment(TournamentFragment.newInstance(model))
    }

    override fun removeTournament(item: Tournament) {
        repository.removeTournament(item.id)
    }

}
