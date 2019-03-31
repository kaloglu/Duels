package com.kaloglu.duels.presentation.tournament

import com.kaloglu.duels.R
import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.presentation.base.BasePresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import javax.inject.Inject

class TournamentPresenter @Inject constructor(
        val repository: TournamentRepository,
        override val genericDependencies: GenericDependencies?
) : BasePresenter<TournamentContract.View>(), TournamentContract.Presenter {

    override fun isFormValid(): Boolean = true

    override fun canSubmitForm(): Boolean = when {
        getView().getTournamentName().isEmpty() -> false
        else -> true
    }

    override fun onSubmitForm() {
        val model = Tournament(getView().getTournamentName())
        repository.add(model).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    getView().showSnackbar(R.string.tournament_form_success_message)
                    fragmentNavigator.popBackStack()
                }
                else -> getView().showSnackbar(it.exception!!.localizedMessage)
            }
        }
    }


}
