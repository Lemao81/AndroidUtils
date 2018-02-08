package com.jueggs.utils.extension

import com.jueggs.utils.randomInt

fun List<Any>.join(separator: String): String = joinToString(separator = separator, transform = { it.toString() })

fun <T> List<T>.random(): T = this[randomInt(0, size - 1)]

fun <T> List<T>.second(): T {
    if (size < 2)
        throw IndexOutOfBoundsException()
    return this[1]
}

fun <T> List<T>.third(): T {
    if (size < 3)
        throw IndexOutOfBoundsException()
    return this[2]
}