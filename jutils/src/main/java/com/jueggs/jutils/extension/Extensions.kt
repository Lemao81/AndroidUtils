package com.jueggs.jutils.extension

import com.jueggs.jutils.*
import java.lang.reflect.*

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.getAllMethods().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.getAllFields().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun <T> Class<T>.getAllMethods(): List<Method> = collectInheritedMethods(mutableListOf(), this)

fun <T> Class<T>.getAllFields(): List<Field> = collectInheritedFields(mutableListOf(), this)