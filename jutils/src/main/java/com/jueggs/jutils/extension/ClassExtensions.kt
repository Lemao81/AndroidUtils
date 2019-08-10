package com.jueggs.jutils.extension

import com.jueggs.jutils.Util
import java.lang.reflect.Field
import java.lang.reflect.Method

fun <T> Class<T>.getAllMethods(): List<Method> = Util.collectInheritedMethods(mutableListOf(), this)

fun <T> Class<T>.getAllFields(): List<Field> = Util.collectInheritedFields(mutableListOf(), this)