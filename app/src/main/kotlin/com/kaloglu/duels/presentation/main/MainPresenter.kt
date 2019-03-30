package com.kaloglu.duels.presentation.main

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.tournament.TournamentFragment
import com.kaloglu.duels.presentation.base.BasePresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import com.kaloglu.duels.utils.extensions.putArgs
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor(override val genericDependencies: GenericDependencies?)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onClearFragmentContainer() = getView().showContentContainer(true)

    override fun onFillFragmentContainer() = getView().showContentContainer(false)

    override fun newTournament() {
        fragmentNavigator
                .setCallback(this)
                .showFragment(TournamentFragment().putArgs())
    }
}
