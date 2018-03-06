package com.jueggs.andutils.util

import android.util.Log
import com.jueggs.andutils.*
import com.jueggs.jutils.EMPTY_STRING

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