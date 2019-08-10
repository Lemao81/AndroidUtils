package com.jueggs.jutils.extension

import java.lang.reflect.Field
import java.lang.reflect.Method

fun Any.findDeclaredMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.declaredMethods.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findMethod(name: String, modifiers: Int = 0x11111111): Method? = javaClass.getAllMethods().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findDeclaredField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.declaredFields.singleOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

fun Any.findField(name: String, modifiers: Int = 0x11111111): Field? = javaClass.getAllFields().firstOrNull { it.name == name && (it.modifiers and modifiers) != 0 }

@Suppress("UNCHECKED_CAST")
fun <T> Any.being(): T = this as? T ?: throw ClassCastException("Cast of ${this.javaClass.name} failed")