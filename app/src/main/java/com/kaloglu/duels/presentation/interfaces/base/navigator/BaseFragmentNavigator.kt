package com.kaloglu.duels.presentation.interfaces.base.navigator

import androidx.fragment.app.FragmentManager
import com.kaloglu.duels.R
import com.kaloglu.duels.navigation.FragmentNavigator
import javax.inject.Inject

class BaseFragmentNavigator @Inject constructor(fragmentManger: FragmentManager)
    : FragmentNavigator(fragmentManger, R.id.fragment_container)
