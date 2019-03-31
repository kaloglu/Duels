package com.kaloglu.duels.mobileui.splash

import com.kaloglu.duels.R
import com.kaloglu.duels.mobileui.base.mvp.BaseMvpActivity
import com.kaloglu.duels.presentation.interfaces.splash.SplashContract

class SplashActivity : BaseMvpActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

    override val contentResourceId = R.layout.activity_splash

    override val snackbarLayoutId: Int = R.id.sign_in_container

}
