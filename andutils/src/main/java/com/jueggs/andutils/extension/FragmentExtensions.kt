package com.jueggs.andutils.extension

import android.app.Activity
import android.support.annotation.*
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import com.jueggs.andutils.isLollipopOrAboveUtil

fun Fragment.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(title, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, arrayResId, onSelectIndex, onSelectString)

fun Fragment.setNavigationTransitions(@TransitionRes enterResId: Int?, @TransitionRes exitResId: Int?, @TransitionRes reenterResId: Int?, @TransitionRes returnResId: Int?) {
    val transitionInflater = TransitionInflater.from(this.context)
    if (enterResId != null)
        enterTransition = transitionInflater.inflateTransition(enterResId)
    if (exitResId != null)
        exitTransition = transitionInflater.inflateTransition(exitResId)
    if (reenterResId != null)
        reenterTransition = transitionInflater.inflateTransition(reenterResId)
    if (returnResId != null)
        returnTransition = transitionInflater.inflateTransition(returnResId)
}