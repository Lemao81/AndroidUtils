package com.jueggs.andutils.extension

fun Int.secondToMillisecond(): Long = this * 1000L

infix fun <T> Boolean.then(param: T): T? = if (this) param else null