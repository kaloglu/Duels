package com.kaloglu.duels.mobileui.interfaces

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager
import com.kaloglu.duels.injection.scopes.PerFragment
import javax.inject.Inject

@PerFragment
class UIStateManager @Inject constructor(val context: Activity) {

    interface UIStatesView {
        fun getLoadingContainer(): View?
        fun getEmptyContainer(): View?
        fun getContentContainer(): View?
        fun getErrorContainer(): View?

        fun getSceneLayout(): ConstraintLayout?
    }

    interface UIStatesPresenter {
        @UiThread
        fun loadingUIState(): Unit?

        @UiThread
        fun emptyUIState(): Unit?

        @UiThread
        fun contentUIState(): Unit?

        @UiThread
        fun errorUIState(): Unit?
    }

    private lateinit var statesView: UIStatesView

    fun initStates(statesView: UIStatesView) {
        this.statesView = statesView
    }

    private fun showUIState(view: View) {
        statesView.getSceneLayout()?.run {
            beginDelayed(this)
            hideAllState()
            view.visibility = View.VISIBLE
        }
    }

    private fun hideAllState() {
        statesView.getLoadingContainer()?.visibility = View.GONE
        statesView.getEmptyContainer()?.visibility = View.GONE
        statesView.getContentContainer()?.visibility = View.GONE
        statesView.getErrorContainer()?.visibility = View.GONE
    }

    fun loadingUIState() = statesView.getLoadingContainer()?.run(::showUIState)

    fun emptyUIState() = statesView.getEmptyContainer()?.run(::showUIState)

    fun contentUIState() = statesView.getContentContainer()?.run(::showUIState)

    fun errorUIState() = statesView.getErrorContainer()?.run(::showUIState)

    private fun beginDelayed(constraintLayout: ConstraintLayout) = TransitionManager.beginDelayedTransition(constraintLayout)

}
