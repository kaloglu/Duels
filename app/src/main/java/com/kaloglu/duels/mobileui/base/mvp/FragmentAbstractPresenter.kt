package com.kaloglu.duels.mobileui.base.mvp

import com.kaloglu.duels.presentation.interfaces.fragment.mvp.FragmentPresenter
import com.kaloglu.duels.presentation.interfaces.fragment.mvp.FragmentView

abstract class FragmentAbstractPresenter<V : FragmentView> : BaseAbstractPresenter<V>(), FragmentPresenter<V>
