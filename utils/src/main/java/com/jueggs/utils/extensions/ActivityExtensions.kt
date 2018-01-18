package com.jueggs.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.support.annotation.DrawableRes
import android.support.annotation.TransitionRes
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import com.jueggs.utils.INVALID_VALUE
import com.jueggs.utils.R
import com.jueggs.utils.isLollipopOrAbove
import org.jetbrains.anko.inputMethodManager
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

fun Activity.hideSoftKeyboard() = inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)

fun Activity.setEnterTransition(@TransitionRes resId: Int = R.transition.fade) {
    if (isLollipopOrAbove())
        window.enterTransition = TransitionInflater.from(this).inflateTransition(resId)
}


//AppCompatActivity
fun AppCompatActivity.setHomeAsBackIcon(@DrawableRes resId: Int) = supportActionBar?.setHomeAsUpIndicator(resId)