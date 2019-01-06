package com.kaloglu.duels.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


/**
 * Created by kaloglu on 6.01.2019.
 */
class RelativeLayoutBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<RelativeLayout>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: RelativeLayout, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: RelativeLayout, dependency: View): Boolean {
        val translationY = Math.min(0f, dependency.translationY - dependency.height)
        child.translationY = translationY
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: RelativeLayout, dependency: View) {
        child.translationY = 0f
    }
}
