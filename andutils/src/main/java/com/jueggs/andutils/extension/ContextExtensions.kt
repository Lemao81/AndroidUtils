package com.jueggs.andutils.extension

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager.RunningAppProcessInfo.*
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
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
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.alert
import org.jetbrains.anko.connectivityManager
import org.jetbrains.anko.intentFor
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

inline fun <reified T : Activity> Context.pendingActivityIntentFor(flag: Int, options: Bundle? = null, requestCode: Int = 0) = PendingIntent.getActivity(this, requestCode, intentFor<T>(), flag, options)

fun Context.pendingActivityIntent(intent: Intent, flag: Int, options: Bundle? = null, requestCode: Int = 0) = PendingIntent.getActivity(this, requestCode, intent, flag, options)

inline fun <reified T : BroadcastReceiver> Context.pendingBroadcastIntentFor(flag: Int, requestCode: Int = 0) = PendingIntent.getBroadcast(this, requestCode, intentFor<T>(), flag)

inline fun <reified T : Service> Context.pendingServiceIntentFor(flag: Int, requestCode: Int = 0) = PendingIntent.getService(this, requestCode, intentFor<T>(), flag)

fun Context.createSettingsIntent(): Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri)

val Context.defaultSharedPrefs: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

val Context.packageUri: Uri
    get() = Uri.parse("package:$packageName")

val Context.isInForeground: Boolean
    get() = activityManager.runningAppProcesses.any { it.importance == IMPORTANCE_FOREGROUND && it.processName == packageName }