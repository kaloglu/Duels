package com.kaloglu.duels.mobileui.base.mvp

import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityPresenter
import com.kaloglu.duels.presentation.interfaces.activity.mvp.ActivityView

abstract class ActivityAbstractPresenter<V : ActivityView> : BaseAbstractPresenter<V>(), ActivityPresenter<V>
