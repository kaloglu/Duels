package com.kaloglu.duels.mobileui.interfaces

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.kaloglu.duels.injection.scopes.PerActivity
import javax.inject.Inject

@PerActivity
class UIStateManager @Inject constructor(val context: Context) {

    private val loadingSet = ConstraintSet()
    private val emptySet = ConstraintSet()
    private val contentSet = ConstraintSet()
    private val errorSet = ConstraintSet()

    private lateinit var sceneLayout: ConstraintLayout

    private var loadingLayout: Int? = null
    private var emptyLayout: Int? = null
    private var contentLayout: Int? = null
    private var errorLayout: Int? = null

    fun initStates(states: UIStates) {
        sceneLayout = states.sceneLayout
        loadingLayout = states.loadingLayout
        emptyLayout = states.emptyLayout
        contentLayout = states.contentLayout

        if (hasLoadingUIState())
            loadingSet.clone(context, loadingLayout!!)

        if (hasEmptyUIState())
            emptySet.clone(context, emptyLayout!!)

        if (hasContentUIState())
            contentSet.clone(context, contentLayout!!)

        if (hasErrorUIState())
            contentSet.clone(context, errorLayout!!)

    }

    private fun hasLoadingUIState() = loadingLayout != null
    private fun hasEmptyUIState() = emptyLayout != null
    private fun hasContentUIState() = contentLayout != null
    private fun hasErrorUIState() = errorLayout != null

    interface UIStates {
        val sceneLayout: ConstraintLayout
        val loadingLayout: Int?
        val emptyLayout: Int?
        val contentLayout: Int?
        val errorLayout: Int?

        fun getContext(): Context
    }

    fun loadingUIState() {
        if (!hasLoadingUIState()) return

        beginDelayed()
        loadingSet.applyTo(sceneLayout)
    }


    fun emptyUIState() {
        if (!hasEmptyUIState()) return

        beginDelayed()
        emptySet.applyTo(sceneLayout)
    }

    fun contentUIState() {
        if (!hasContentUIState()) return

        beginDelayed()
        contentSet.applyTo(sceneLayout)
    }

    fun errorUIState() {
        if (!hasErrorUIState()) return

        beginDelayed()
        errorSet.applyTo(sceneLayout)
    }

    private fun beginDelayed() = TransitionManager.beginDelayedTransition(sceneLayout)

}
