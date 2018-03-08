package com.jueggs.andutils.extension

import android.annotation.SuppressLint
import android.content.*
import android.graphics.drawable.Drawable
import android.support.annotation.ArrayRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ArrayAdapter
import com.jueggs.andutils.createSharedElement
import org.jetbrains.anko.*

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo.isConnectedOrConnecting

fun Context.createSimpleSpinnerAdapter(vararg elements: String): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: List<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: Array<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun Context.createSimpleSpinnerAdapter(@ArrayRes arrayResId: Int): ArrayAdapter<String> = createSimpleSpinnerAdapter(getStringArray(arrayResId))

fun Context.getStringArray(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)
fun Context.getIntArray(@ArrayRes resId: Int): IntArray = resources.getIntArray(resId)
fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)
fun Context.createSharedElement(view: View, @StringRes resId: Int): android.util.Pair<View, String> = createSharedElement(view, resources.getString(resId))

fun Context.showConfirmDialog(title: CharSequence?, message: CharSequence, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
        alert(message, title) {
            yesButton { confirmAction() }
            noButton { denyAction() }
        }.show()

fun Context.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
        showConfirmDialog(if (titleResId != null) getString(titleResId) else null, getString(messageResId), confirmAction, denyAction)

fun Context.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        selector(title, items, { _, index ->
            onSelectIndex(index)
            onSelectString(items[index].toString())
        })

fun Context.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        showSelection(if (titleResId != null) getString(titleResId) else null, items, onSelectIndex, onSelectString)

fun Context.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
        showSelection(if (titleResId != null) getString(titleResId) else null, getStringArray(arrayResId).asList(), onSelectIndex, onSelectString)