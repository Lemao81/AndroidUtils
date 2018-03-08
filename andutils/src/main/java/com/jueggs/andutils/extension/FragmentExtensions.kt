package com.jueggs.andutils.extension

import android.support.annotation.*
import android.support.v4.app.Fragment

fun Fragment.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(title, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, items, onSelectIndex, onSelectString)

fun Fragment.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        context?.showSelection(titleResId, arrayResId, onSelectIndex, onSelectString)