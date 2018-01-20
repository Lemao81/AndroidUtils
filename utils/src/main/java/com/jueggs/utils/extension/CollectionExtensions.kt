package com.jueggs.utils.extension

import com.jueggs.utils.randomInt

fun List<String>.join(separator: String): String = joinToString(separator) { it }

fun <T> List<T>.random(): T = this[randomInt(0, size - 1)]