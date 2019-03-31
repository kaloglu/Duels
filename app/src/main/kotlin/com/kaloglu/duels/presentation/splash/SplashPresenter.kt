package com.kaloglu.duels.presentation.splash

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.presentation.base.BasePresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.splash.SplashContract
import javax.inject.Inject

@PerActivity
class SplashPresenter @Inject constructor(override val genericDependencies: GenericDependencies)
    : BasePresenter<SplashContract.View>(), SplashContract.Presenter {

    override fun onLogin() = activityNavigator
            .toMainActivity()
            .singleTop()
            .clearTop()
            .finishThis()
            .navigate()
}



