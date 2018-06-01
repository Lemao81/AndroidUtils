package com.jueggs.andutils.extension

import android.animation.*
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.*
import android.graphics.drawable.*
import android.os.Build
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.widget.ArrayAdapter
import com.jueggs.andutils.*
import com.jueggs.andutils.helper.ColorAnimator
import org.jetbrains.anko.*

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo.isConnectedOrConnecting

fun Context.createSimpleSpinnerAdapter(vararg elements: String): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: List<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: Array<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun Context.createSimpleSpinnerAdapter(@ArrayRes arrayResId: Int): ArrayAdapter<String> = createSimpleSpinnerAdapter(getStringArray(arrayResId))

fun Context.getStringArray(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)
fun Context.getStringList(@ArrayRes resId: Int): List<String> = getStringArray(resId).asList()
fun Context.getIntArray(@ArrayRes resId: Int): IntArray = resources.getIntArray(resId)
fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)
fun Context.createSharedElement(view: View, @StringRes resId: Int): android.util.Pair<View, String> = createSharedElement(view, resources.getString(resId))

fun Context.showConfirmDialog(title: CharSequence?, message: CharSequence, confirmAction: (Unit) -> Unit, denyAction: (Unit) -> Unit = {}) =
        alert(message, title) {
            yesButton { confirmAction(Unit) }
            noButton { denyAction(Unit) }
        }.show()

fun Context.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: (Unit) -> Unit, denyAction: (Unit) -> Unit = {}) =
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

fun Context.animateColor(@ColorRes from: Int, @ColorRes to: Int): ColorAnimator {
    return if (isLollipopOrAboveUtil()) {
        ColorAnimator(ValueAnimator.ofArgb(getColorCompat(from), getColorCompat(to)))
    } else {
        val anim = ValueAnimator()
        anim.setIntValues(from, to)
        anim.setEvaluator(ArgbEvaluator())
        return ColorAnimator(anim)
    }
}

fun Context.drawableAsByteArray(@DrawableRes resId: Int): ByteArray? = (ContextCompat.getDrawable(this, resId) as? BitmapDrawable)?.bitmap?.toByteArray()

fun Context.dpToPixel(dips: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips.toFloat(), resources.displayMetrics).toInt()

fun Context.getColorCompat(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.backgroundColor(@ColorRes resId: Int): Drawable = ColorDrawable(getColorCompat(resId))