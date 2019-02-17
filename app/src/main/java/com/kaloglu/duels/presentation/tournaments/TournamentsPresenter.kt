package com.kaloglu.duels.presentation.tournaments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.data.filters.Filters
import com.kaloglu.duels.data.repository.tournaments.TournamentsRepository
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.mobileui.interfaces.UIStateManager.UIStateType
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import javax.inject.Inject

class TournamentsPresenter @Inject constructor(
        private val repository: TournamentsRepository,
        override var uiStateManager: UIStateManager?,
        override val genericDependencies: GenericDependencies
) : BaseAbstractPresenter<TournamentsContract.View>(), TournamentsContract.Presenter {
    private lateinit var isSignedIn: LiveData<Boolean>
    private val filters = MutableLiveData<Filters>()

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


    override fun loadTournaments() {
        getUIState(UIStateType.LOADING)
        isSignedIn = object : LiveData<Boolean>() {
            override fun onActive() {
                super.onActive()
                value = FirebaseAuth.getInstance().currentUser != null
            }
        }

        repository.getTournaments(null).observe(getView())
    }

    override fun getUIState(state: UIStateManager.UIStateType) = uiStateManager?.getState(state)
            ?: Unit

}
