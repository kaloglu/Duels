package com.kaloglu.duels.presentation.interfaces.activity.mvp

import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter

interface ActivityPresenter<out V : ActivityView> : BasePresenter<V>
