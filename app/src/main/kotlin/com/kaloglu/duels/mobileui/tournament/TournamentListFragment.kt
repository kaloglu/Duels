package com.kaloglu.duels.mobileui.tournament

import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.kaloglu.duels.R
import com.kaloglu.duels.adapter.tournament.TournamentListAdapter
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager.UIStateType
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
    : BaseMvpFragment<TournamentContract.ListPresenter>(), TournamentContract.ListView {

    private lateinit var adapter: TournamentListAdapter

    override val resourceLayoutId = R.layout.fragment_tournament_list

    override fun getSceneLayout(): ConstraintLayout? = tournamentListScene

    override fun getContainer(uiStateType: UIStateType): LinearLayout? {
        return when (uiStateType) {
            UIStateType.LOADING -> loading
            UIStateType.EMPTY -> empty
            UIStateType.CONTENT -> content
            UIStateType.ERROR -> null
        }
    }

    override fun onLoading() = presenter.getUIState(UIStateType.LOADING)

    override fun onEmpty() = presenter.getUIState(UIStateType.EMPTY)

    override fun onSuccess(data: List<Model>) {
        presenter.getUIState(UIStateType.CONTENT)

        adapter.items = data
    }

    override fun onError(errorMessage: String?, data: List<Model>?) =
            presenter.getUIState(UIStateType.ERROR)


    override fun initUserInterface(rootView: View) {
        adapter = recyclerViewTournamentList
                .setup(TournamentListAdapter())

        adapter
                .setItemClickListener(::onClickItem)
                .setViewClickListener(::onClickView)
    }

    override fun onClickItem(model: Model) = presenter.openDetail(model)

    override fun onClickView(model: Model, view: View) {
//        BottomSheetDialog(view.context).run {
//            setContentView(
//                    layoutInflater.inflate(R.layout.tournament_bottom_sheet_dialog, null)
//            )
//            show()
//        }

        presenter.remove(model)
    }

    override fun onPresenterAttached() {
        super.onPresenterAttached()
        presenter.observe()
    }

    companion object {
        fun newInstance() = TournamentListFragment()
    }

}
