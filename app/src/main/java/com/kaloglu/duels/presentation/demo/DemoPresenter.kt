package com.kaloglu.duels.presentation.demo

import com.google.android.gms.tasks.OnCompleteListener
import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.demo.DemoContract
import javax.inject.Inject

class DemoPresenter @Inject constructor(
        private val localStorage: LocalStorage,
        activityNavigator: ActivityNavigator
) : BaseAbstractPresenter<Any, DemoContract.View<Any>>(activityNavigator), DemoContract.Presenter {

    override fun getNextActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(): OnCompleteListener<Void> =
            OnCompleteListener {
                localStorage.cleaSample()
                activityNavigator
                        .toSplashScreen()
                        .finishThis()
                        .navigate()
            }
}
