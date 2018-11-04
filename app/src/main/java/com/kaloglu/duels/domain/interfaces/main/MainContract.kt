package com.kaloglu.duels.domain.interfaces.main

import com.kaloglu.duels.domain.interfaces.base.mvp.BasePresenter
import com.kaloglu.duels.domain.interfaces.base.mvp.BaseView

interface MainContract {

    interface View : BaseView {
        fun setNavigationOnClick(onClick: (android.view.View) -> Unit)
    }

    interface Presenter : BasePresenter<View>
}
