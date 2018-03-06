package com.jueggs.jutils

import java.util.*

var random = Random(System.currentTimeMillis())

/**
 * Random int between min and max, including them
 */
fun randomInt(min: Int, max: Int): Int = random.nextInt(max - min + 1) + min

fun randomBoolean() = random.nextBoolean()

fun randomDouble(min: Double = 0.0, max: Double = 1.0) = (max - min) * random.nextDouble() + min

fun <T> randomSelectWeighted(left: T, right: T, weightTowardsLeft: Double): T {
    assert(weightTowardsLeft < 1.0)
    return if (random.nextDouble() < weightTowardsLeft) left else right
}

fun newUUID(): String = UUID.randomUUID().toString()