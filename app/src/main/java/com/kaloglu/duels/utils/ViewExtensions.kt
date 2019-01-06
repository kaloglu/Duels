package com.kaloglu.duels.utils

import android.animation.Animator
import android.view.animation.Interpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by kaloglu on 6.01.2019.
 */

@JvmOverloads
fun FloatingActionButton.with(
        predicate: Boolean = true,
        value: Float? = null,
        interpolator: Interpolator = LinearOutSlowInInterpolator(),
        onStart: (FloatingActionButton) -> Unit = {},
        onEnd: (FloatingActionButton) -> Unit = {},
        onCancel: (FloatingActionButton) -> Unit = {},
        onRepeat: (FloatingActionButton) -> Unit = {}
) {
    if (!predicate)
        return
    
    animate().apply {
        if (value != null) {
            alpha(value)
            scaleX(value)
            scaleY(value)
        }

        duration = 300
        setInterpolator(interpolator)
        setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) = onStart(this@with)

            override fun onAnimationEnd(animation: Animator) = onEnd(this@with)

            override fun onAnimationCancel(animation: Animator) = onCancel(this@with)

            override fun onAnimationRepeat(animation: Animator) = onRepeat(this@with)
        })
    }.start()
}

