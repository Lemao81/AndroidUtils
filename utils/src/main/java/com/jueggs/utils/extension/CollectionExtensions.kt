package com.jueggs.utils.extension

import com.jueggs.utils.randomInt

fun List<Any>.join(separator: String): String = joinToString(separator = separator, transform = { it.toString() })

fun <T> List<T>.random(): T = this[randomInt(0, size - 1)]