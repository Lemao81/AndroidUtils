package com.jueggs.jutils.extension

import com.jueggs.jutils.*
import com.nhaarman.mockito_kotlin.KStubbing
import kotlinx.coroutines.experimental.runBlocking
import org.mockito.BDDMockito
import org.mockito.stubbing.OngoingStubbing
import java.lang.reflect.*

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.getAllMethods().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.getAllFields().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun <T> Class<T>.getAllMethods(): List<Method> = collectInheritedMethods(mutableListOf(), this)

fun <T> Class<T>.getAllFields(): List<Field> = collectInheritedFields(mutableListOf(), this)

fun <T : Any, R> KStubbing<T>.onBlocking(methodCall: suspend T.() -> R): OngoingStubbing<R> {
    val mockField = KStubbing::class.java.getDeclaredField("mock").apply { isAccessible = true }
    val mockInstance = mockField.get(this) as? T ?: throw IllegalStateException("Could not access mock field of KStubbing class")

    return runBlocking { org.mockito.Mockito.`when`(mockInstance.methodCall()) }
}

infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willReturn(value: T): BDDMockito.BDDMyOngoingStubbing<T> = willReturn(value)

infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willThrow(throwable: Throwable): BDDMockito.BDDMyOngoingStubbing<T> = willThrow(throwable)