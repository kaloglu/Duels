package com.kaloglu.duels.mobileui.tournaments

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager.UIStateType
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import kotlinx.android.synthetic.main.fragment_tournaments.*
import kotlinx.android.synthetic.main.tournaments_content.*
import kotlinx.android.synthetic.main.tournaments_empty.*
import kotlinx.android.synthetic.main.tournaments_loading.*

class TournamentsFragment
    : BaseMvpFragment<TournamentsContract.Presenter>(), TournamentsContract.View {

    override val resourceLayoutId = R.layout.fragment_tournaments

    override fun getSceneLayout(): ConstraintLayout? = tournamentsScene

    override fun getContainer(uiStateType: UIStateType) =
            when (uiStateType) {
                UIStateType.LOADING -> loading
                UIStateType.EMPTY -> empty
                UIStateType.CONTENT -> content
                UIStateType.ERROR -> null
            }

    override fun initUserInterface(rootView: View) = Unit

    override fun onLoading() {
        presenter.getUIState(UIStateType.LOADING)
    }

    override fun onSuccess(data: List<Tournament>) {
        when (data.isNullOrEmpty()) {
            true -> presenter.getUIState(UIStateType.EMPTY)
            else -> presenter.getUIState(UIStateType.CONTENT)
        }
    }

    override fun onError(errorMessage: String?, data: List<Tournament>?) {
        presenter.getUIState(UIStateType.ERROR)
    }

    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.loadTournaments()
    }

    companion object {
        fun newInstance() =
                TournamentsFragment()
    }

}
