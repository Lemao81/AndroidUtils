package com.jueggs.andutils

import android.os.Build

fun isLollipopOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isMarshmallowOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatOrAboveUtil() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N