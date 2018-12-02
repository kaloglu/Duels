package com.kaloglu.duels.domain.main

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.domain.interfaces.main.MainContract
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.navigation.ActivityNavigator
import javax.inject.Inject

class MainPresenter @Inject constructor(private val activityNavigator: ActivityNavigator)
    : BaseAbstractPresenter<MainContract.View>(), MainContract.Presenter {

    override fun signOut(): OnCompleteListener<Void> =
            OnCompleteListener {
                activityNavigator
                        .toSplashScreen()
                        .navigate()
            }
}
