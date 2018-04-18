package com.jueggs.andutils

import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.EditText
import com.jueggs.jutils.randomInt
import org.apache.commons.validator.routines.EmailValidator
import java.time.*

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun executeDelayed(milliseconds: Long, action: () -> Unit) = Handler().postDelayed(action, milliseconds)

fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

inline fun <reified T> checkCast(obj: Any) {
    if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
}

fun hasText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

fun isValidEmailAddress(email: CharSequence?) = !email.isNullOrBlank() && EmailValidator.getInstance().isValid(email.toString())

fun isInvalidEmailAddress(email: CharSequence?) = !isValidEmailAddress(email)