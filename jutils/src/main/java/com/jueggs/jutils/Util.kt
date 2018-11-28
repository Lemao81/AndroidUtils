package com.jueggs.jutils

import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.UUID

object Util {
    val newUUID: String = UUID.randomUUID().toString()

    fun cropToRange(bottomLimit: Float, topLimit: Float, value: Float) = Math.min(Math.max(bottomLimit, value), topLimit)

    fun cropToRange(bottomLimit: Int, topLimit: Int, value: Int) = Math.min(Math.max(bottomLimit, value), topLimit)

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

    fun areAllNull(vararg params: Any?): Boolean = params.all { it == null }

    fun <TParam1, TParam2> withNotNull(param1: TParam1?, param2: TParam2?, block: (TParam1, TParam2) -> Unit) {
        if (areAllNull(param1, param2)) return
        param1?.let { p1 -> param2?.let { p2 -> block(p1, p2) } }
    }

    fun <TParam1, TParam2, TParam3> withNotNull(param1: TParam1?, param2: TParam2?, param3: TParam3?, block: (TParam1, TParam2, TParam3) -> Unit) {
        if (areAllNull(param1, param2, param3)) return
        param1?.let { p1 -> param2?.let { p2 -> param3?.let { p3 -> block(p1, p2, p3) } } }
    }
}