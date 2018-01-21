package com.jueggs.utils.extension

fun Int.secondToMillisecond(): Long = this * 1000L

infix fun <T> Boolean.then(param: T): T? = if (this) param else null