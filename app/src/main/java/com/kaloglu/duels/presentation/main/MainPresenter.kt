package com.kaloglu.duels.presentation.main

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor(
        override val activityNavigator: ActivityNavigator
) : BaseAbstractPresenter<Any, MainContract.View>(), MainContract.Presenter {

    override fun getNextActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
