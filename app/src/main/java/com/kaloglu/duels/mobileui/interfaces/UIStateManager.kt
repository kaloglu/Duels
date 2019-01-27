package com.kaloglu.duels.mobileui.interfaces

import android.app.Activity
import android.view.View
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager
import com.kaloglu.duels.injection.scopes.PerFragment
import com.kaloglu.duels.utils.hide
import com.kaloglu.duels.utils.show
import javax.inject.Inject

@PerFragment
class UIStateManager @Inject constructor(val context: Activity) {

    private lateinit var statesView: UIStatesView

    enum class UIStateType {
        LOADING,
        EMPTY,
        CONTENT,
        ERROR
    }

    interface UIStatesView {

        fun getSceneLayout(): ConstraintLayout?
        fun getContainer(uiStateType: UIStateType): View?
    }

    interface UIStatesPresenter {

        @UiThread
        fun getUIState(state: UIStateType): Unit?
    }

    fun initStates(statesView: UIStatesView) {
        this.statesView = statesView
    }

    fun getState(state: UIStateType) = statesView.showUIState(state)

    private fun UIStatesView.showUIState(state: UIStateType) {
        getSceneLayout()?.let(TransitionManager::beginDelayedTransition)
        hideAllState(this)
        getContainer(state).show()
    }

    private fun hideAllState(uiStatesView: UIStatesView) {
        UIStateType.values().forEach {
            uiStatesView.getContainer(it).hide()
        }
    }

}

