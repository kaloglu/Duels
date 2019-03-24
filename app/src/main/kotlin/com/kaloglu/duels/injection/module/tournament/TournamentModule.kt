package com.kaloglu.duels.injection.module.tournament

import com.kaloglu.duels.data.repository.tournament.TournamentRepository
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.mobileui.tournament.TournamentFragment
import com.kaloglu.duels.mobileui.tournament.TournamentListFragment
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournament.TournamentContract
import com.kaloglu.duels.presentation.tournament.TournamentListPresenter
import com.kaloglu.duels.presentation.tournament.TournamentPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TournamentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFragment
        fun listPresenter(
                repository: TournamentRepository,
                uiStateManager: UIStateManager,
                genericDependencies: GenericDependencies
        ): TournamentContract.ListPresenter =
                TournamentListPresenter(repository, uiStateManager, genericDependencies)

        @JvmStatic
        @Provides
        @PerFragment
        fun presenter(
                repository: TournamentRepository,
                genericDependencies: GenericDependencies
        ): TournamentContract.Presenter =
                TournamentPresenter(repository, genericDependencies)

    }

    @Binds
    @PerFragment
    abstract fun tournamentList(fragment: TournamentListFragment): BaseFragment

    @Binds
    @PerFragment
    abstract fun tournament(fragment: TournamentFragment): BaseFragment

}
