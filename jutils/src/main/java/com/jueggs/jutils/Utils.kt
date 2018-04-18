package com.jueggs.jutils

import org.mockito.Mockito
import java.time.LocalDate
import java.time.temporal.*
import java.util.*

var random = Random(System.currentTimeMillis())

/**
 * Random int between min and max, including them
 */
fun randomInt(min: Int, max: Int): Int = random.nextInt(max - min + 1) + min

fun randomLong(min: Long, max: Long): Long {
    val diff = (max - min).toInt()
    return random.nextInt(diff) + min
}

fun randomBoolean() = random.nextBoolean()

fun randomFloat(min: Float = 0f, max: Float = 1f) = (max - min) * random.nextFloat() + min

fun randomDouble(min: Double = 0.0, max: Double = 1.0) = (max - min) * random.nextDouble() + min

fun randomDouble2Decimal(min: Double = 0.0, max: Double = 1.0) = Math.floor(randomDouble(min, max) * 100) / 100

fun randomFloat2Decimal(min: Float = 0f, max: Float = 1f) = (Math.floor((randomFloat(min, max) * 100).toDouble()) / 100).toFloat()

fun randomDate(min: Date, max: Date): Date = Date(randomLong(min.time, max.time))

fun <T> randomSelectWeighted(left: T, right: T, weightTowardsLeft: Double): T {
    assert(weightTowardsLeft < 1.0)
    return if (random.nextDouble() < weightTowardsLeft) left else right
}

fun newUUID(): String = UUID.randomUUID().toString()

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

private fun <T> uninitialized(): T = null as T