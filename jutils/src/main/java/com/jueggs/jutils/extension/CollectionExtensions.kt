package com.jueggs.jutils.extension

import com.jueggs.jutils.Random

fun List<Any>.join(separator: String): String = joinToString(separator = separator, transform = { it.toString() })

fun <T> List<T>.second(): T = this[1]

fun <T> List<T>.third(): T = this[2]

val <T> Collection<T>.maxIndex
    get() = this.size - 1