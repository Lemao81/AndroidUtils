package com.jueggs.utils.extension

fun List<String>.join(separator: String): String = joinToString(separator) { it }