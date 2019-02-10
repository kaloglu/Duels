package com.kaloglu.duels.presentation.interfaces.fragment.mvp

import com.kaloglu.duels.presentation.interfaces.base.mvp.BasePresenter

interface FragmentPresenter<out V : FragmentView> : BasePresenter<V>
