package com.jueggs.utils.extensions

fun List<String>.join(separator: String): String = joinToString(separator) { it }