package com.kaloglu.duels.mobileui.interfaces

import android.view.View

import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager
import com.kaloglu.duels.injection.scopes.PerFragment
import javax.inject.Inject

@PerFragment
class UIStateManager @Inject constructor() {

    private lateinit var statesView: UIStatesView

    interface UIStatesView {

        fun getCurrentState() = currentState

        fun getSceneLayout(): ConstraintLayout?
        fun getContainer(uiStateType: UIStateType): View?
    }

    interface UIStatesPresenter {

        @UiThread
        fun getUIState(state: UIStateType)
    }

    fun initStates(statesView: UIStatesView) {
        this.statesView = statesView
    }

    fun getState(state: UIStateType) = statesView.showUIState(state)

    private fun UIStatesView.showUIState(state: UIStateType) {
        currentState = state
        getSceneLayout()?.let(TransitionManager::beginDelayedTransition)
        hideAllState(this)
        getContainer(state).show()
    }

    private fun hideAllState(uiStatesView: UIStatesView) {
        UIStateType.values().forEach {
            uiStatesView.getContainer(it).hide()
        }
    }

    private fun View?.show(show: Boolean = true) {
        this?.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun View?.hide() = show(false)

    companion object {
        private var currentState: UIStateType = UIStateType.LOADING
    }

}
