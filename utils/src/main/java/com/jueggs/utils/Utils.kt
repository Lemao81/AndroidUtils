package com.jueggs.utils

import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.EditText
import org.apache.commons.validator.routines.EmailValidator
import java.util.*

var random = Random(System.currentTimeMillis())

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)

fun logTagged(tag: String, level: Int, text: Any?, prefix: String = EMPTY_STRING) {
    val msg = if (text == null) "<null>" else if (prefix.isNotBlank()) "$prefix:\t\t\t$text" else text.toString()
    when (level) {
        LOG_LEVEL_DEBUG -> Log.d(tag, msg)
        LOG_LEVEL_ERROR -> Log.e(tag, msg)
    }
}

fun logDebug(text: Any?, prefix: String = EMPTY_STRING) = logTagged(TAG_DEBUG, LOG_LEVEL_DEBUG, text, prefix)

fun logError(text: Any?, prefix: String = EMPTY_STRING) = logTagged(TAG_ERROR, LOG_LEVEL_ERROR, text, prefix)

fun logNetwork(text: Any?, prefix: String = EMPTY_STRING) = logTagged(TAG_NETWORK, LOG_LEVEL_DEBUG, text, prefix)

fun logUnhandledException(throwable: Throwable) = logExceptionInternal(TAG_UNHANDLED_EXCEPTION, throwable)

fun logException(throwable: Throwable) = logExceptionInternal(TAG_EXCEPTION, throwable)

private fun logExceptionInternal(tag: String, throwable: Throwable) {
    logTagged(tag, LOG_LEVEL_ERROR, "${throwable.message}\n", EMPTY_STRING)
    logTagged(tag, LOG_LEVEL_ERROR, throwable.stackTrace.joinToString("\n", transform = { it.toString() }), EMPTY_STRING)
}

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun executeDelayed(milliseconds: Long, action: () -> Unit) = Handler().postDelayed(action, milliseconds)

/**
 * Random int between min and max, including them
 */
fun randomInt(min: Int, max: Int): Int = random.nextInt(max - min + 1) + min

fun randomBoolean() = random.nextBoolean()

fun newGuid(): String = UUID.randomUUID().toString()

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

fun isValidEmailAddress(email: String) = EmailValidator.getInstance().isValid(email)