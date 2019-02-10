package com.kaloglu.duels.presentation.interfaces.demo

import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.presentation.interfaces.base.mvp.BaseView

interface DemoContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
