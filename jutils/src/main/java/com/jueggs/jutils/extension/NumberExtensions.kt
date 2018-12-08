package com.jueggs.jutils.extension

import kotlin.math.max
import kotlin.math.min

infix operator fun <T> Int.times(action: () -> T): List<T> = (0..this).map { action() }

fun Double.cropTo(min: Double, max: Double) = min(max(min, this), max)
fun Double.cropTo(min: Int, max: Double) = min(max(min.toDouble(), this), max)
fun Double.cropTo(min: Double, max: Int) = min(max(min, this), max.toDouble())

fun Float.cropTo(min: Float, max: Float) = min(max(min, this), max)
fun Float.cropTo(min: Int, max: Float) = min(max(min.toFloat(), this), max)
fun Float.cropTo(min: Float, max: Int) = min(max(min, this), max.toFloat())

fun Int.cropTo(min: Int, max: Int) = min(max(min, this), max)