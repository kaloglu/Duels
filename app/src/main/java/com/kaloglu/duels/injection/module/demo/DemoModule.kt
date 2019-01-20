package com.kaloglu.duels.injection.module.demo

import com.kaloglu.duels.data.LocalStorage
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.navigation.ActivityNavigator
import com.kaloglu.duels.presentation.demo.DemoPresenter
import com.kaloglu.duels.presentation.interfaces.demo.DemoContract
import dagger.Module
import dagger.Provides

@Module()
abstract class DemoModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerFragment
        fun presenter(
                localStorage: LocalStorage,
                activityNavigator: ActivityNavigator
        ): DemoContract.Presenter =
                DemoPresenter(localStorage, activityNavigator)

    }

}
