package com.kaloglu.duels.injection.module.tournaments

import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.tournaments.TournamentsContract
import com.kaloglu.duels.presentation.tournaments.TournamentsPresenter
import dagger.Module
import dagger.Provides

@Module
abstract class TournamentsModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFragment
        fun presenter(localStorage: LocalStorage, activityNavigator: ActivityNavigator): TournamentsContract.Presenter =
                TournamentsPresenter(localStorage, activityNavigator)

    }

}
