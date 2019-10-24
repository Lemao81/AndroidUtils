package com.jueggs.andutils.extension

import android.animation.ValueAnimator
import com.jueggs.andutils.helper.ColorAnimator
import kotlin.reflect.KFunction1

fun ColorAnimator.update(func: KFunction1<Int, Unit>): ValueAnimator = valueAnimator.also {
    it.addUpdateListener { animator -> func(animator.animatedValue as Int) }
}

fun ValueAnimator.startDelayed(delay: Long) {
    startDelay = delay
    start()
}

inline fun ValueAnimator.doOnHalfTime(crossinline action: (animator: ValueAnimator) -> Unit) {
    val halfDuration = duration / 2
    var isAfterHalfTime = false
    addUpdateListener {
        if (!isAfterHalfTime && it.currentPlayTime >= halfDuration) {
            action(this)
            isAfterHalfTime = true
        }
    }
}