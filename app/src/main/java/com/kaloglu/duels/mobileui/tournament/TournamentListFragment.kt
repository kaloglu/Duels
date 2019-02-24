package com.kaloglu.duels.mobileui.tournament

import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager.UIStateType
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import kotlinx.android.synthetic.main.fragment_tournament_list.*
import kotlinx.android.synthetic.main.tournament_list_content.*
import kotlinx.android.synthetic.main.tournament_list_empty.*
import kotlinx.android.synthetic.main.tournament_list_loading.*

class TournamentListFragment
    : BaseMvpFragment<TournamentContract.ListPresenter>(), TournamentContract.ListView {

    override val resourceLayoutId = R.layout.fragment_tournament_list

    override fun getSceneLayout(): ConstraintLayout? = tournamentListScene

    override fun getContainer(uiStateType: UIStateType) =
            when (uiStateType) {
                UIStateType.LOADING -> loading
                UIStateType.EMPTY -> empty
                UIStateType.CONTENT -> content
                UIStateType.ERROR -> null
            }

    override fun onLoading() = presenter.getUIState(UIStateType.LOADING)

    override fun onEmpty() = presenter.getUIState(UIStateType.EMPTY)

    override fun onSuccess(data: List<Tournament>) = presenter.getUIState(UIStateType.CONTENT)

    override fun onError(errorMessage: String?, data: List<Tournament>?) =
            presenter.getUIState(UIStateType.ERROR)

    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.observeTournamentList()
    }

    companion object {
        fun newInstance() =
                TournamentListFragment()
    }

}
