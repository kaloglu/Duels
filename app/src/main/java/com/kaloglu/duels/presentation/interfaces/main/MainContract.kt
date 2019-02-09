package com.kaloglu.duels.presentation.interfaces.main

import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityPresenter
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityView

interface MainContract {

    interface View : ActivityView

    interface Presenter : ActivityPresenter<View>
}
