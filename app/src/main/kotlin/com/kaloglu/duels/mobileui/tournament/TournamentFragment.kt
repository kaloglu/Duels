package com.kaloglu.duels.mobileui.tournament

import android.os.Bundle
import android.view.View
import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpFragment
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.utils.extensions.onActionResInSoftKeyboard
import kotlinx.android.synthetic.main.fragment_tournament.*

class TournamentFragment : BaseMvpFragment<TournamentContract.Presenter>(), TournamentContract.View {
    override lateinit var submitButtonView: View

    override val resourceLayoutId = R.layout.fragment_tournament

    override fun initUserInterface(rootView: View) {
        tournamentForm_Name.onActionResInSoftKeyboard(R.integer.create_tournament) { submitButtonView.callOnClick() }
        setSubmitButton(tournamentForm_Submit) {
            presenter.submitForm()
        }
    }

    override fun getName(): String = tournamentForm_Name.text.toString()

    companion object {
        fun newInstance(model: Model): TournamentFragment {
            val fragment = TournamentFragment()
            val args = Bundle()
            args.putSerializable("model", model)
            fragment.arguments = args
            return fragment
        }
    }

}
