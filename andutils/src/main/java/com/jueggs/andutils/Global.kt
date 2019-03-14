package com.jueggs.andutils

import android.os.Build
import com.log4k.d
import com.log4k.e

fun isLollipopOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isMarshmallowOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isOreoOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

inline fun <reified T> T.d(throwable: Throwable) = this.d(throwable.message ?: "", throwable)
inline fun <reified T> T.e(throwable: Throwable) = this.e(throwable.message ?: "", throwable)