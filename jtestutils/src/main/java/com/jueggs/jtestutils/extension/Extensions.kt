package com.jueggs.jtestutils.extension

import com.nhaarman.mockito_kotlin.KStubbing
import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import org.mockito.stubbing.OngoingStubbing

fun <T : Any, R> KStubbing<T>.onBlocking(methodCall: suspend T.() -> R): OngoingStubbing<R> {
    val mockField = KStubbing::class.java.getDeclaredField("mock").apply { isAccessible = true }
    val mockInstance = mockField.get(this) as? T ?: throw IllegalStateException("Could not access mock field of KStubbing class")

    return runBlocking { org.mockito.Mockito.`when`(mockInstance.methodCall()) }
}

infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willReturn(value: T): BDDMockito.BDDMyOngoingStubbing<T> = willReturn(value)

infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwable: Throwable): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwable)