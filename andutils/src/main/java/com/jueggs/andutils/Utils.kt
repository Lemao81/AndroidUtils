package com.jueggs.andutils

import android.os.Build
import android.os.Handler
import android.view.View
import android.widget.EditText
import androidx.core.os.postDelayed
import org.apache.commons.validator.routines.EmailValidator
import org.jetbrains.anko.collections.toAndroidPair

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = pairOf(view, transitionName).toAndroidPair()

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

inline fun <reified T> checkCast(obj: Any) {
    if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
}

fun hasText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

fun isValidEmailAddress(email: CharSequence?) = !email.isNullOrBlank() && EmailValidator.getInstance().isValid(email.toString())

fun isInvalidEmailAddress(email: CharSequence?) = !isValidEmailAddress(email)

fun <A, B> pairOf(first: A, second: B): Pair<A, B> = Pair(first, second)

inline fun postDelayed(delayInMillis: Long, token: Any? = null, crossinline action: () -> Unit): Runnable = Handler().postDelayed(delayInMillis, token, action)