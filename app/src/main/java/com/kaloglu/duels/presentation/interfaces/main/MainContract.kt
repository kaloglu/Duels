package com.kaloglu.duels.presentation.interfaces.main

import com.kaloglu.duels.navigation.FragmentNavigator
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityPresenter
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityView

interface MainContract {

    interface View : ActivityView {
        fun showContentContainer(show: Boolean = true)
    }

    interface Presenter : ActivityPresenter<View>, FragmentNavigator.FragmentCallback {
        fun newTournament()
    }
}
