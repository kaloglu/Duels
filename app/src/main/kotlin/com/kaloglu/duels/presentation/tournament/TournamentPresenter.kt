package com.kaloglu.duels.presentation.tournament

import com.kaloglu.duels.R
import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.presentation.base.BasePresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import javax.inject.Inject

class TournamentPresenter @Inject constructor(
        private val repository: TournamentRepository,
        override val genericDependencies: GenericDependencies
) : BasePresenter<TournamentContract.View>(), TournamentContract.Presenter {

    override fun isFormValid(): Boolean = true

    override fun canSubmitForm(): Boolean = when {
        getView().getName().isEmpty() -> false
        else -> true
    }

    override fun onSubmitForm() {
        val model = Model(getView().getName())
        repository.add(model).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    getView().showSnackbar(R.string.tournament_form_success_message)
                    genericDependencies.fragmentNavigator.popBackStack()
                }
                else -> getView().showSnackbar(it.exception!!.localizedMessage)
            }
        }
    }


}