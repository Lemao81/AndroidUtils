package com.jueggs.andutils

import android.os.Build
import com.jueggs.andutils.logging.Log
import com.jueggs.andutils.logging.Log.*
import com.log4k.d
import com.log4k.e

fun isEclairOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR
fun isLollipopOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isMarshmallowOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isOreoOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

inline fun <reified T> T.d(throwable: Throwable) = this.d(throwable.message ?: "", throwable)

inline fun <reified T> T.d(log: Log) {
    when (log) {
        METHOD -> {
            val stackTrace = Thread.currentThread().stackTrace
            val secondElement = stackTrace[1]
            this.d("${secondElement.className}.${secondElement.methodName}()")
        }
    }
}

inline fun <reified T> T.e(throwable: Throwable) = this.e(throwable.message ?: "", throwable)