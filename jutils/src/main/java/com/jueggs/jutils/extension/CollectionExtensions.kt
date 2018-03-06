package com.jueggs.jutils.extension

import com.jueggs.jutils.randomInt

fun List<Any>.join(separator: String): String = joinToString(separator = separator, transform = { it.toString() })

fun <T> List<T>.random(): T = this[randomInt(0, size - 1)]

fun <T> List<T>.second(): T = this[1]

fun <T> List<T>.third(): T = this[2]