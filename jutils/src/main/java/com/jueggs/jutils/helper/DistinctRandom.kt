package com.jueggs.jutils.helper

import com.jueggs.jutils.extension.random
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class DistinctRandom<T>(private val values: List<T>) {
    private val set = mutableSetOf<T>()

    operator fun invoke(): T {
        return runBlocking {
            withTimeout(3000) {
                var next: T
                do {
                    next = values.random()
                } while (set.contains(next))
                set.add(next)
                next
            }
        }
    }

    fun reset() = set.clear()
}