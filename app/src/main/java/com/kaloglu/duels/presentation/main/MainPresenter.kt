package com.kaloglu.duels.presentation.main

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.base.mvp.ActivityAbstractPresenter
import com.kaloglu.duels.presentation.base.GenericDependencies
import com.kaloglu.duels.presentation.interfaces.main.MainContract
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor(
        override val genericDependencies: GenericDependencies
) : ActivityAbstractPresenter<MainContract.View>(), MainContract.Presenter
