package com.jueggs.jutils

import kotlinx.coroutines.runBlocking
import org.mockito.*
import java.lang.Math.*
import java.lang.reflect.*
import java.util.*

var random = Random(System.currentTimeMillis())

/**
 * Random int between min and max, including them
 */
fun randomInt(min: Int = 0, max: Int = Int.MAX_VALUE - 1): Int = random.nextInt(max - min + 1) + min

fun randomLong(): Long = (randomInt() * randomInt()).toLong()

fun randomBoolean() = random.nextBoolean()

fun randomFloat(min: Float = 0f, max: Float = 1f) = (max - min) * random.nextFloat() + min

fun randomDouble(min: Double = 0.0, max: Double = 1.0) = (max - min) * random.nextDouble() + min

fun randomDouble2Decimal(min: Double = 0.0, max: Double = 1.0) = floor(randomDouble(min, max) * 100) / 100

fun randomFloat2Decimal(min: Float = 0f, max: Float = 1f) = (floor((randomFloat(min, max) * 100).toDouble()) / 100).toFloat()

fun randomDate(): Date = Date(randomLong())

fun <T> randomSelectWeighted(left: T, right: T, weightTowardsLeft: Double): T {
    assert(weightTowardsLeft < 1.0)
    return if (random.nextDouble() < weightTowardsLeft) left else right
}

fun newUUID(): String = UUID.randomUUID().toString()

//TODO remove if confirmed; replaced by mockito-kotlin
//fun <T> any(): T {
//    Mockito.any<T>()
//    return uninitialized()
//}

//private fun <T> uninitialized(): T = null as T

fun cropToRange(bottomLimit: Float, topLimit: Float, value: Float) = min(max(bottomLimit, value), topLimit)

fun cropToRange(bottomLimit: Int, topLimit: Int, value: Int) = min(max(bottomLimit, value), topLimit)

fun <T> collectInheritedFields(list: MutableList<Field>, type: Class<T>): List<Field> {
    list.addAll(type.declaredFields)

    if (type.superclass != null)
        collectInheritedFields(list, type.superclass)

    return list
}

fun <T> collectInheritedMethods(list: MutableList<Method>, type: Class<T>): List<Method> {
    list.addAll(type.declaredMethods)

    if (type.superclass != null)
        collectInheritedMethods(list, type.superclass)

    return list
}

fun <T> givenSuspended(block: suspend () -> T) = BDDMockito.given(runBlocking { block() })