package com.kaloglu.duels.navigation

import android.app.Activity
import android.content.Intent
import androidx.annotation.AnimRes

class NavigationCreator internal constructor(private val activity: Activity) {

    private var intent: Intent? = null
    private var animationPair: Pair<Int, Int>? = null
    private var requestCode: Int = 0
    private var forResult = false
    private var finishThis = false

    fun navigate() {

        intent?.also {
            when {
                forResult -> activity.startActivityForResult(it, requestCode)
                else -> activity.startActivity(it)
            }
        }

        animationPair?.let { (enterAnim, exitAnim) ->
            activity.overridePendingTransition(enterAnim, exitAnim)
        }

        if (finishThis) activity.finish()
    }

    fun intent(intent: Intent): NavigationCreator {
        this.intent = intent
        return this
    }

    fun forResult(requestCode: Int): NavigationCreator {
        forResult = true
        this.requestCode = requestCode
        return this
    }

    fun clearBackStack(): NavigationCreator {
        intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        return this
    }

    fun singleTop(): NavigationCreator {
        intent!!.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        return this
    }

    fun clearTop(): NavigationCreator {
        intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        return this
    }

    fun finishThis(): NavigationCreator {
        finishThis = true
        return this
    }

    fun withAnimation(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int): NavigationCreator {
        animationPair = Pair(enterAnim, exitAnim)
        return this
    }
}
