package com.kaloglu.duels.presentation.base

import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.mobileui.interfaces.UIStateManager
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.navigation.FragmentNavigator
import javax.inject.Inject

@PerFragment
class GenericListDependencies @Inject constructor(
        override val firebaseAuth: FirebaseAuth,
        override val activityNavigator: ActivityNavigator,
        override val fragmentNavigator: FragmentNavigator,
        var uiStateManager: UIStateManager
) : GenericDependencies(firebaseAuth, activityNavigator, fragmentNavigator)
