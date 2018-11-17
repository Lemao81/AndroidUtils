package com.jueggs.andutils.test

import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicInteger

object CountingIdlingResource {
    private val counter = AtomicInteger(0)
    private var callback: () -> Unit = {}

    fun isIdleNow() = counter.get() == 0

    fun registerIdleTransitionCallback(callback: () -> Unit) {
        this.callback = callback
    }

    fun increment() = counter.getAndIncrement()

    fun decrement() {
        val count = counter.decrementAndGet()
        if (count == 0) callback()
        if (count < 0) throw IllegalStateException("Counter has been corrupted")
    }
}