package com.jueggs.jutils.extension

infix operator fun <T> Int.times(action: () -> T): List<T> = (0..this).map { action() }