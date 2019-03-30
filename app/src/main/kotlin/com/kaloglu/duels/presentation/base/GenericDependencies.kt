package com.kaloglu.duels.presentation.base

import com.google.firebase.auth.FirebaseAuth
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.navigation.FragmentNavigator
import javax.inject.Inject

@PerActivity
open class GenericDependencies @Inject constructor(
        open val firebaseAuth: FirebaseAuth,
        open val activityNavigator: ActivityNavigator,
        open val fragmentNavigator: FragmentNavigator
)
