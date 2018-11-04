package com.kaloglu.duels.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.kaloglu.duels.injection.scopes.PerActivity
import com.kaloglu.duels.mobileui.main.MainActivity
import javax.inject.Inject

@PerActivity
class ActivityNavigator @Inject constructor(private val activity: Activity) {

    fun finishCurrentActivity() = NavigationCreator(activity).finishThis()

    fun openExternalUrl(url: String) = NavigationCreator(activity)
            .intent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    fun toRepoListActivity() = NavigationCreator(activity)
            .intent(MainActivity.newIntent(activity))

}
