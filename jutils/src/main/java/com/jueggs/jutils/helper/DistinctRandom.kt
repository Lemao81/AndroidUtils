package com.jueggs.jutils.helper

import com.jueggs.jutils.extension.random
import kotlinx.coroutines.experimental.*
import java.util.concurrent.TimeUnit

class DistinctRandom<T>(private val values: List<T>) {
    private val set = mutableSetOf<T>()

    fun next(): T {
        return runBlocking {
            withTimeout(3, TimeUnit.SECONDS) {
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