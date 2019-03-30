package com.kaloglu.duels.mobileui.tournament

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.tournament.TournamentListAdapter
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpListFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateType
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.utils.extensions.setItemClickListener
import com.kaloglu.duels.utils.extensions.setViewClickListener
import com.kaloglu.duels.utils.extensions.setup
import kotlinx.android.synthetic.main.fragment_tournament_list.*
import kotlinx.android.synthetic.main.tournament_list_content.*
import kotlinx.android.synthetic.main.tournament_list_empty.*
import kotlinx.android.synthetic.main.tournament_list_loading.*

class TournamentListFragment
    : BaseMvpListFragment<Model, TournamentContract.ListView, TournamentContract.ListPresenter>()
        , TournamentContract.ListView {

    private lateinit var adapter: TournamentListAdapter

    override val resourceLayoutId = R.layout.fragment_tournament_list

    override fun getSceneLayout(): ConstraintLayout? = tournamentListScene

    override fun getContainer(uiStateType: UIStateType) = when (uiStateType) {
        UIStateType.LOADING -> loading
        UIStateType.EMPTY -> empty
        UIStateType.CONTENT -> content
        UIStateType.ERROR -> null
    }

    override fun initUserInterface(rootView: View) {
        super.initUserInterface(rootView)

        adapter = recyclerViewTournamentList
                .setup(TournamentListAdapter())

        adapter
                .setItemClickListener(::onClickItem)
                .setViewClickListener(::onClickView)
    }

    override fun onSuccess(data: List<Model>) {
        super.onSuccess(data)
        adapter.items = data
    }

    override fun onClickView(model: Model, view: View) {
        super.onClickView(model, view)
        when (view.id) {
            R.id.tournamentMoreButton -> showBottomMenu(model)
        }
    }

}
