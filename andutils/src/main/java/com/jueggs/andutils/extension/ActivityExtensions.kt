package com.jueggs.andutils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Parcelable
import android.transition.TransitionInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.TransitionRes
import androidx.core.util.toAndroidPair
import com.jueggs.andutils.R
import com.jueggs.andutils.isLollipopOrAboveUtil
import com.jueggs.jutils.INVALID
import com.jueggs.jutils.pairOf
import org.jetbrains.anko.inputMethodManager
import org.jetbrains.anko.intentFor
import java.security.InvalidParameterException

fun Activity.getIntExtra(key: String): Int = intent.getIntExtra(key, INVALID)

fun Activity.getStringExtra(key: String): String? = intent.getStringExtra(key)

fun Activity.getBooleanExtra(key: String): Boolean = intent.getBooleanExtra(key, false)

inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = intent.getParcelableExtra<T>(key)

inline fun <reified T : Parcelable> Activity.getParcelableArrayListExtra(key: String) = intent.getParcelableArrayListExtra<T>(key)

fun Activity.getStringArrayListExtra(key: String) = intent.getStringArrayListExtra(key)

inline fun <reified T> Activity.setResultOk(vararg data: Pair<String, T>) {
    val intent = Intent()
    when {
        Parcelable::class.java.isAssignableFrom(T::class.java) -> data.forEach { (key, value) -> intent.putExtra(key, value as Parcelable) }
        T::class == Int::class -> data.forEach { (key, value) -> intent.putExtra(key, value as Int) }
        T::class == Double::class -> data.forEach { (key, value) -> intent.putExtra(key, value as Double) }
        T::class == String::class -> data.forEach { (key, value) -> intent.putExtra(key, value as String) }
        T::class == Boolean::class -> data.forEach { (key, value) -> intent.putExtra(key, value as Boolean) }
        else -> throw InvalidParameterException("Unknown type ${T::class.java.simpleName}, extend when control")
    }
    setResult(Activity.RESULT_OK, intent)
}

fun Activity.hideStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}

fun Activity.hideKeyboard() = inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

fun Activity.setNavigationTransitions(@TransitionRes enterResId: Int?, @TransitionRes exitResId: Int?, @TransitionRes reenterResId: Int?, @TransitionRes returnResId: Int?) {
    if (isLollipopOrAboveUtil()) {
        val transitionInflater = TransitionInflater.from(this)
        if (enterResId != null)
            window.enterTransition = transitionInflater.inflateTransition(enterResId)
        if (exitResId != null)
            window.exitTransition = transitionInflater.inflateTransition(exitResId)
        if (reenterResId != null)
            window.reenterTransition = transitionInflater.inflateTransition(reenterResId)
        if (returnResId != null)
            window.returnTransition = transitionInflater.inflateTransition(returnResId)
    }
}

fun Activity.setEnterTransition(@TransitionRes resId: Int = R.transition.fade) {
    if (isLollipopOrAboveUtil())
        window.enterTransition = TransitionInflater.from(this).inflateTransition(resId)
}

fun Activity.setExitTransition(@TransitionRes resId: Int = R.transition.fade) {
    if (isLollipopOrAboveUtil())
        window.exitTransition = TransitionInflater.from(this).inflateTransition(resId)
}

fun Activity.setReenterTransition(@TransitionRes resId: Int = R.transition.fade) {
    if (isLollipopOrAboveUtil())
        window.reenterTransition = TransitionInflater.from(this).inflateTransition(resId)
}

fun Activity.setReturnTransition(@TransitionRes resId: Int = R.transition.fade) {
    if (isLollipopOrAboveUtil())
        window.returnTransition = TransitionInflater.from(this).inflateTransition(resId)
}

@SuppressLint("NewApi")
inline fun <reified T : Any> Activity.startActivityWithTransition(sharedElements: List<View>, vararg extras: Pair<String, Any?>) {
    if (isLollipopOrAboveUtil()) {
        val options = ActivityOptions.makeSceneTransitionAnimation(this, *sharedElements.map { pairOf(it, it.transitionName).toAndroidPair() }.toTypedArray()).toBundle()
        startActivity(intentFor<T>(*extras), options)
    } else
        startActivity(intentFor<T>(*extras))
}

@SuppressLint("NewApi")
inline fun <reified T : Any> Activity.startActivityWithTransition(sharedElements: List<View>, extras: List<Pair<String, Any?>>) = startActivityWithTransition<T>(sharedElements, *extras.toTypedArray())

@SuppressLint("NewApi")
inline fun <reified T : Any> Activity.startActivityForResultWithTransition(requestCode: Int, sharedElements: List<View>, vararg extras: Pair<String, Any?>) {
    if (isLollipopOrAboveUtil()) {
        val options = ActivityOptions.makeSceneTransitionAnimation(this, *sharedElements.map { pairOf(it, it.transitionName).toAndroidPair() }.toTypedArray()).toBundle()
        startActivityForResult(intentFor<T>(*extras), requestCode, options)
    } else
        startActivityForResult(intentFor<T>(*extras), requestCode)
}

fun Activity.setStatusbarColor(@ColorRes resId: Int) {
    if (isLollipopOrAboveUtil())
        window.statusBarColor = colorResToInt(resId)
}