package com.jueggs.jutils

import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.UUID

object Util {
    val newUUID: String = UUID.randomUUID().toString()

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

    fun areAllNull(vararg params: Any?) = params.all { it == null }

    fun isAnyNull(vararg params: Any?) = params.any { it == null }

    inline fun <T, R> withNotNull(receiver: T?, block: T.() -> R) = receiver?.let { with(it, block) } ?: Unit

    fun <TParam1, TParam2> withNotNull(param1: TParam1?, param2: TParam2?, block: (TParam1, TParam2) -> Unit) = param1?.let { p1 -> param2?.let { p2 -> block(p1, p2) } } ?: Unit

    fun <TParam1, TParam2, TParam3> withNotNull(param1: TParam1?, param2: TParam2?, param3: TParam3?, block: (TParam1, TParam2, TParam3) -> Unit) = param1?.let { p1 ->
        param2?.let { p2 ->
            param3?.let { p3 -> block(p1, p2, p3) }
        }
    } ?: Unit
}