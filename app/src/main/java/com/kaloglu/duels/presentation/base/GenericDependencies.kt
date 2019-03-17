package com.kaloglu.duels.presentation.base

import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.navigation.FragmentNavigator
import javax.inject.Inject

@PerActivity
class GenericDependencies @Inject constructor(
        val activityNavigator: ActivityNavigator,
        val fragmentNavigator: FragmentNavigator
)
