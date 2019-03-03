package com.kaloglu.duels.mobileui.tournament

import android.os.Bundle
import android.view.View
import com.kaloglu.duels.R
import com.kaloglu.duels.data.model.Tournament
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import kotlinx.android.synthetic.main.fragment_tournament.*

class TournamentFragment : BaseMvpFragment<TournamentContract.Presenter>(), TournamentContract.View {
    override lateinit var submitButtonView: View

    override val resourceLayoutId = R.layout.fragment_tournament

    override fun initUserInterface(rootView: View) {
        setSubmitButton(tournamentForm_Submit) {
            presenter.submitForm()
        }
    }

    override fun getName(): String = tournamentForm_Name.text.toString()

    companion object {
        fun newInstance(item: Tournament): TournamentFragment {
            val fragment = TournamentFragment()
            val args = Bundle()
            args.putSerializable("item", item)
            fragment.arguments = args
            return fragment
        }
    }

}
