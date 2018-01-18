package com.jueggs.utils.extensions

import java.lang.reflect.Field
import java.lang.reflect.Method

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }