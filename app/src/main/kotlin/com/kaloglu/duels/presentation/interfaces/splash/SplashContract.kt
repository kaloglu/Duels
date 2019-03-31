package com.kaloglu.duels.presentation.interfaces.splash

import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpPresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.MvpView

interface SplashContract {

    interface View : MvpView

    interface Presenter : MvpPresenter<View>
}
