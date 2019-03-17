package com.kaloglu.duels.presentation.interfaces.main

import com.kaloglu.duels.navigation.FragmentNavigator
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView

interface MainContract {

    interface View : MvpView {
        fun showContentContainer(show: Boolean = true)
    }

    interface Presenter : MvpPresenter<View>, FragmentNavigator.FragmentCallback {
        fun newTournament()
    }
}
