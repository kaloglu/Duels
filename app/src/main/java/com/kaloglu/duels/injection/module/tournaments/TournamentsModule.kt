package com.kaloglu.duels.injection.module.tournaments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kaloglu.duels.data.repository.tournaments.TournamentsRepository
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.mobileui.base.BaseFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.mobileui.tournaments.TournamentsFragment
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import com.kaloglu.duels.presentation.tournaments.TournamentsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TournamentsModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFragment
        fun presenter(
                repository: TournamentsRepository,
                uiStateManager: UIStateManager,
                genericDependencies: GenericDependencies
        ): TournamentsContract.Presenter =
                TournamentsPresenter(
                        repository,
                        uiStateManager,
                        genericDependencies
                )

    }

    @Binds
    @PerFragment
    abstract fun tournaments(fragment: TournamentsFragment): BaseFragment

}
