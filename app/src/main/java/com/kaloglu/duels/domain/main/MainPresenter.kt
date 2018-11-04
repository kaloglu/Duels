package com.kaloglu.duels.domain.main

import com.kaloglu.duels.domain.interfaces.main.MainContract
import com.kaloglu.duels.mobileui.base.mvp.BaseAbstractPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor()
    : BaseAbstractPresenter<MainContract.View>(), MainContract.Presenter
