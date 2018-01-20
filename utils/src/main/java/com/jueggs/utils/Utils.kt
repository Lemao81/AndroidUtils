package com.jueggs.utils

import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import java.util.*

var random = Random(System.currentTimeMillis())

fun createSharedElement(view: View, transitionName: String): android.util.Pair<View, String> = android.util.Pair(view, transitionName)

fun logTagged(tag: String, level: Int, text: Any, prefix: String) {
    val msg = if (!prefix.isEmpty()) "$prefix:    $text" else text.toString()
    when (level) {
        LOG_DEBUG -> Log.d(tag, msg)
        LOG_ERROR -> Log.e(tag, msg)
    }
}

fun logDebug(text: Any, prefix: String = EMPTY_STRING) = logTagged(TAG_DEBUG, LOG_DEBUG, text, prefix)

fun logNetwork(text: Any, prefix: String = EMPTY_STRING) = logTagged(TAG_NETWORK, LOG_DEBUG, text, prefix)

fun logUnhandledException(throwable: Throwable) {
    logTagged(TAG_UNHANDLED_EXCEPTION, LOG_ERROR, "${throwable.message}\n", EMPTY_STRING)
    logTagged(TAG_UNHANDLED_EXCEPTION, LOG_ERROR, throwable.stackTrace.joinToString("\n", transform = { it.toString() }), EMPTY_STRING)
}

fun isLollipopOrAboveUtil(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun executeDelayed(milliseconds: Long, action: () -> Unit) = Handler().postDelayed(action, milliseconds)

/**
 * Random int between min and max, including them
 */
fun randomInt(min: Int, max: Int): Int = random.nextInt(max - min + 1) + min

fun randomBoolean() = random.nextBoolean()

fun newGuid(): String = UUID.randomUUID().toString()

fun viewsVisibleIf(condition: Boolean, vararg views: View) {
    views.forEach { if (condition) it.visibility = View.VISIBLE else it.visibility = View.GONE }
}

fun viewsGoneIf(condition: Boolean, vararg views: View) {
    views.forEach { if (condition) it.visibility = View.GONE else it.visibility = View.VISIBLE }
}