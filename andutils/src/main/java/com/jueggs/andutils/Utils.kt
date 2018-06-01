package com.jueggs.andutils

import android.os.Build
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.core.os.postDelayed
import org.jetbrains.anko.collections.toAndroidPair
import java.lang.Math.*
import java.lang.reflect.*

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = pairOf(view, transitionName).toAndroidPair()

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun viewsVisibleIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }

fun viewsGoneIf(condition: Boolean, vararg views: View) = views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }

inline fun <reified T> checkCast(obj: Any) {
    if (!T::class.java.isAssignableFrom(obj::class.java)) throw TypeCastException("class ${obj::class.java.simpleName} must be assignable from ${T::class.java.simpleName}")
}

fun hasText(vararg inputFields: EditText): Boolean = inputFields.all { !it.text.isNullOrEmpty() }

val CharSequence?.isValidEmail: Boolean
    get() = !this.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

val CharSequence?.isInvalidEmail: Boolean
    get() = this.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun <A, B> pairOf(first: A, second: B): Pair<A, B> = Pair(first, second)

inline fun postDelayed(delayInMillis: Long, token: Any? = null, crossinline action: () -> Unit): Runnable = Handler().postDelayed(delayInMillis, token, action)

fun measureView(widthMeasureSpec: Int, heightMeasureSpec: Int, desiredWidth: Int, desiredHeight: Int, setMeasuredDimension: (Int, Int) -> Unit) {
    val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
    val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
    val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
    val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

    val width = when (widthMode) {
        View.MeasureSpec.EXACTLY -> widthSize
        View.MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
        View.MeasureSpec.UNSPECIFIED -> desiredWidth
        else -> desiredWidth
    }
    val height = when (heightMode) {
        View.MeasureSpec.EXACTLY -> heightSize
        View.MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
        View.MeasureSpec.UNSPECIFIED -> desiredHeight
        else -> desiredHeight
    }

    setMeasuredDimension(width, height)
}

fun isVerticalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_VERTICAL

fun isHorizontalScroll(axes: Int) = axes == ViewCompat.SCROLL_AXIS_HORIZONTAL