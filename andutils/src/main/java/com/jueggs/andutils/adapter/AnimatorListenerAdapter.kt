package com.jueggs.andutils.adapter

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.view.ViewPropertyAnimator

class AnimatorListenerImpl : AnimatorListener {
    private var onAnimationRepeatFunc: ((Animator) -> Unit)? = null
    private var onAnimationEndFunc: ((Animator) -> Unit)? = null
    private var onAnimationCancelFunc: ((Animator) -> Unit)? = null
    private var onAnimationStartFunc: ((Animator) -> Unit)? = null

    override fun onAnimationStart(animation: Animator?) = animation?.let { onAnimationStartFunc?.invoke(animation) } ?: Unit

    override fun onAnimationEnd(animation: Animator?) = animation?.let { onAnimationEndFunc?.invoke(animation) } ?: Unit

    override fun onAnimationRepeat(animation: Animator?) = animation?.let { onAnimationRepeatFunc?.invoke(animation) } ?: Unit

    override fun onAnimationCancel(animation: Animator?) = animation?.let { onAnimationCancelFunc?.invoke(animation) } ?: Unit

    fun _onAnimationStart(func: (Animator) -> Unit) {
        onAnimationStartFunc = func
    }

    fun _onAnimationEnd(func: (Animator) -> Unit) {
        onAnimationEndFunc = func
    }

    fun _onAnimationRepeat(func: (Animator) -> Unit) {
        onAnimationRepeatFunc = func
    }

    fun _onAnimationCancel(func: (Animator) -> Unit) {
        onAnimationCancelFunc = func
    }
}

fun Animator._addListener(block: AnimatorListenerImpl.() -> Unit) = addListener(AnimatorListenerImpl().apply(block))

fun ViewPropertyAnimator._setListener(block: AnimatorListenerImpl.() -> Unit) = setListener(AnimatorListenerImpl().apply(block))