package com.kaloglu.duels.presentation.tournament

import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.mobileui.tournament.TournamentFragment
import com.kaloglu.duels.presentation.base.BaseListPresenter
import com.kaloglu.duels.presentation.base.GenericListDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.Model
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.utils.extensions.putArgs
import javax.inject.Inject

class TournamentListPresenter @Inject constructor(
        override val repository: TournamentRepository,
        override val genericDependencies: GenericListDependencies
) : BaseListPresenter<Model, TournamentContract.ListView>(), TournamentContract.ListPresenter {

    override fun openDetail(model: Model) {
        showFragment(
                TournamentFragment().putArgs {
                    putSerializable("model", model)
                }
        )
    }
}
