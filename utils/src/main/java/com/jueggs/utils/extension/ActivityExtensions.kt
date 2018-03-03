package com.jueggs.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Parcelable
import android.support.annotation.DrawableRes
import android.support.annotation.TransitionRes
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import com.jueggs.jutils.INVALID_VALUE
import com.jueggs.utils.R
import com.jueggs.utils.isLollipopOrAboveUtil
import org.jetbrains.anko.inputMethodManager
import org.jetbrains.anko.intentFor
import java.security.InvalidParameterException

//Activity
fun Activity.getIntExtra(key: String): Int = intent.getIntExtra(key, INVALID_VALUE)

fun Activity.getStringExtra(key: String): String? = intent.getStringExtra(key)

fun Activity.getBooleanExtra(key: String): Boolean = intent.getBooleanExtra(key, false)

inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = intent.getParcelableExtra<T>(key)

inline fun <reified T : Parcelable> Activity.getParcelableArrayListExtra(key: String) = intent.getParcelableArrayListExtra<T>(key)

fun Activity.getStringArrayListExtra(key: String) = intent.getStringArrayListExtra(key)

inline fun <reified T> Activity.setResultOk(vararg data: Pair<String, T>) {
    val intent = Intent()
    when {
        Parcelable::class.java.isAssignableFrom(T::class.java) -> data.forEach { intent.putExtra(it.first, it.second as Parcelable) }
        T::class == Int::class -> data.forEach { intent.putExtra(it.first, it.second as Int) }
        T::class == Double::class -> data.forEach { intent.putExtra(it.first, it.second as Double) }
        T::class == String::class -> data.forEach { intent.putExtra(it.first, it.second as String) }
        T::class == Boolean::class -> data.forEach { intent.putExtra(it.first, it.second as Boolean) }
        else -> throw InvalidParameterException("Unknown type ${T::class.java.simpleName}, extend when control")
    }
    setResult(Activity.RESULT_OK, intent)
}

fun Activity.hideStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}

fun Activity.hideSoftKeyboardExt() = inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)

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
        val options = ActivityOptions.makeSceneTransitionAnimation(this, *sharedElements.map { android.util.Pair(it, it.transitionName) }.toTypedArray()).toBundle()
        startActivity(intentFor<T>(*extras), options)
    } else
        startActivity(intentFor<T>(*extras))
}

@SuppressLint("NewApi")
inline fun <reified T : Any> Activity.startActivityWithTransition(sharedElements: List<View>, extras: List<Pair<String, Any?>>) = startActivityWithTransition<T>(sharedElements, *extras.toTypedArray())

@SuppressLint("NewApi")
inline fun <reified T : Any> Activity.startActivityForResultWithTransition(requestCode: Int, sharedElements: List<View>, vararg extras: Pair<String, Any?>) {
    if (isLollipopOrAboveUtil()) {
        val options = ActivityOptions.makeSceneTransitionAnimation(this, *sharedElements.map { android.util.Pair(it, it.transitionName) }.toTypedArray()).toBundle()
        startActivityForResult(intentFor<T>(*extras), requestCode, options)
    } else
        startActivityForResult(intentFor<T>(*extras), requestCode)
}


//AppCompatActivity
fun AppCompatActivity.setHomeBackIcon(@DrawableRes resId: Int) = supportActionBar?.setHomeAsUpIndicator(resId)

fun AppCompatActivity.setPaddingToStatusBarHeight(view: View) {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resId <= 0) return
    view.setPadding(0, resources.getDimensionPixelSize(resId), 0, 0)
}

//FragmentActivity
fun FragmentActivity.finishAfterTransitionCompat() {
    if (isLollipopOrAboveUtil()) finishAfterTransition()
    else supportFinishAfterTransition()
}

fun FragmentActivity.postponeEnterTransitionCompat() {
    if (isLollipopOrAboveUtil()) postponeEnterTransition()
    else supportPostponeEnterTransition()
}

fun FragmentActivity.startPostponedEnterTransitionCompat() {
    if (isLollipopOrAboveUtil()) startPostponedEnterTransition()
    else supportStartPostponedEnterTransition()
}