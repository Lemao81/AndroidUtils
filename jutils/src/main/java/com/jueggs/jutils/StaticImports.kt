package com.jueggs.jutils

import org.hamcrest.Matcher
import org.hamcrest.core.IsEqual
import org.junit.Assert
import java.util.concurrent.TimeUnit

val MILLISECONDS: TimeUnit
    get() = TimeUnit.MILLISECONDS

val SECONDS: TimeUnit
    get() = TimeUnit.SECONDS

val MINUTES: TimeUnit
    get() = TimeUnit.MINUTES

fun <T> assertThat(actual: T, matcher: Matcher<T>) = Assert.assertThat(actual, matcher)

fun <T> assertTrue(condition: Boolean) = Assert.assertTrue(condition)

fun <T> assertFalse(condition: Boolean) = Assert.assertFalse(condition)

fun <T> equalTo(operand: T) = IsEqual.equalTo(operand)