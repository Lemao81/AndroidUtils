package com.jueggs.utils

import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.EditText
import com.jueggs.jutils.*
import org.apache.commons.validator.routines.EmailValidator
import java.util.*

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun executeDelayed(milliseconds: Long, action: () -> Unit) = Handler().postDelayed(action, milliseconds)

fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

fun measureView(widthMeasureSpec: Int, heightMeasureSpec: Int, desiredWidth: Int, desiredHeight: Int, setMeasuredDimension: (Int, Int) -> Unit) {
    val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
    val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
    val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
    val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

    val width = when (widthMode) {
        View.MeasureSpec.EXACTLY -> widthSize
        View.MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
        View.MeasureSpec.UNSPECIFIED -> desiredWidth
        else -> desiredWidth
    }
    val height = when (heightMode) {
        View.MeasureSpec.EXACTLY -> heightSize
        View.MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
        View.MeasureSpec.UNSPECIFIED -> desiredHeight
        else -> desiredHeight
    }

    setMeasuredDimension(width, height)
}

inline fun <reified T> checkCast(obj: Any) {
    if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
}

fun hasText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

fun isValidEmailAddress(email: CharSequence?) = !email.isNullOrBlank() && EmailValidator.getInstance().isValid(email.toString())

fun isInvalidEmailAddress(email: CharSequence?) = !isValidEmailAddress(email)