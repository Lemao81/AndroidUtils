package com.jueggs.andutils.extension

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.preference.PreferenceManager
import android.util.TypedValue
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.jueggs.andutils.Util.createSharedElement
import com.jueggs.andutils.helper.ColorAnimator
import com.jueggs.andutils.isLollipopOrAboveUtil
import org.jetbrains.anko.alert
import org.jetbrains.anko.connectivityManager
import org.jetbrains.anko.noButton
import org.jetbrains.anko.selector
import org.jetbrains.anko.yesButton

@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false

fun Context.createSimpleSpinnerAdapter(vararg elements: String): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: List<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun <T> Context.createSimpleSpinnerAdapter(elements: Array<T>): ArrayAdapter<T> = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements.toMutableList())
fun Context.createSimpleSpinnerAdapter(@ArrayRes arrayResId: Int): ArrayAdapter<String> = createSimpleSpinnerAdapter(getStringArray(arrayResId))

fun Context.getStringArray(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)
fun Context.getStringList(@ArrayRes resId: Int): List<String> = getStringArray(resId).asList()
fun Context.getIntArray(@ArrayRes resId: Int): IntArray = resources.getIntArray(resId)
fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)
fun Context.createSharedElement(view: View, @StringRes resId: Int): android.util.Pair<View, String> = createSharedElement(view, resources.getString(resId))

fun Context.showConfirmDialog(title: CharSequence?, message: CharSequence, confirmAction: () -> Unit, denyAction: () -> Unit = {}) {
    alert(message, title) {
        yesButton { confirmAction() }
        noButton { denyAction() }
    }.show()
}

fun Context.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: () -> Unit, denyAction: () -> Unit = {}) =
    showConfirmDialog(if (titleResId != null) getString(titleResId) else null, getString(messageResId), confirmAction, denyAction)

fun Context.showConfirmDialog(@StringRes titleResId: Int?, @StringRes messageResId: Int, confirmAction: () -> Unit) =
    showConfirmDialog(if (titleResId != null) getString(titleResId) else null, getString(messageResId), confirmAction)

fun Context.showSelection(title: CharSequence?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    selector(title, items) { _, index ->
        onSelectIndex(index)
        onSelectString(items[index].toString())
    }

fun Context.showSelection(@StringRes titleResId: Int?, items: List<CharSequence>, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    showSelection(if (titleResId != null) getString(titleResId) else null, items, onSelectIndex, onSelectString)

fun Context.showSelection(@StringRes titleResId: Int?, @ArrayRes arrayResId: Int, onSelectIndex: (Int) -> Unit = {}, onSelectString: (String) -> Unit = {}) =
    showSelection(if (titleResId != null) getString(titleResId) else null, getStringArray(arrayResId).asList(), onSelectIndex, onSelectString)

fun Context.animateColor(@ColorRes from: Int, @ColorRes to: Int): ColorAnimator {
    return if (isLollipopOrAboveUtil()) {
        ColorAnimator(ValueAnimator.ofArgb(this.colorResToInt(from), this.colorResToInt(to)))
    } else {
        val anim = ValueAnimator()
        anim.setIntValues(from, to)
        anim.setEvaluator(ArgbEvaluator())
        return ColorAnimator(anim)
    }
}

fun Context.drawableAsByteArray(@DrawableRes resId: Int): ByteArray? = (ContextCompat.getDrawable(this, resId) as? BitmapDrawable)?.bitmap?.toByteArray()

fun Context.dipToPixel(dips: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips.toFloat(), resources.displayMetrics).toInt()

fun Context.colorResToInt(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.doWithNetworkConnection(action: () -> Unit): () -> Boolean {
    val condition = this::isNetworkConnected
    if (condition())
        action()
    return condition
}

val Context.defaultSharedPrefs: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

val Context.packageUri: Uri
    get() = Uri.parse("package:$packageName")