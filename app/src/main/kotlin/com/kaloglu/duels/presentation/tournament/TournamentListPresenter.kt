package com.kaloglu.duels.presentation.tournament

import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.domain.model.Tournament
import com.kaloglu.duels.mobileui.tournament.TournamentFragment
import com.kaloglu.duels.presentation.base.BaseListPresenter
import com.kaloglu.duels.presentation.base.GenericListDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.utils.extensions.putArgs
import java.util.*
import javax.inject.Inject

class TournamentListPresenter @Inject constructor(
        override val repository: TournamentRepository,
        override val genericDependencies: GenericListDependencies
) : BaseListPresenter<Tournament, TournamentContract.ListView>(), TournamentContract.ListPresenter {

    override fun openDetail(model: Tournament) {
        showFragment(
                TournamentFragment().putArgs {
                    putSerializable("model", model)
                }
        )
    }

    override fun createTournament() {
        repository.add(Tournament("new Tournament ${Date()}"))
    }
}
