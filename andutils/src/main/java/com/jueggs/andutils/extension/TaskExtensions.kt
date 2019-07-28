package com.jueggs.andutils.extension

import com.google.android.gms.tasks.Task
import com.jueggs.andutils.d
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun Task<Void>.then(action: () -> Unit) = addOnCompleteListener { action() }

fun Task<Void>.success(action: () -> Unit) = addOnSuccessListener { action() }

fun Task<Void>.fail(action: () -> Unit) = addOnFailureListener { action() }

fun Task<Void>.logFailure() = addOnFailureListener { d(it) }

fun <T> Task<T>.then(action: (Task<T>) -> Unit) = addOnCompleteListener { action(it) }

fun <T> Task<T>.success(action: (T) -> Unit) = addOnSuccessListener { action(it) }

fun <T> Task<T>.fail(action: (Exception) -> Unit) = addOnFailureListener { action(it) }

suspend fun <T> Task<T>.await(): T = suspendCoroutine { continuation ->
    addOnSuccessListener { continuation.resume(it) }.addOnFailureListener { continuation.resumeWithException(it) }
}

suspend fun <T> Task<T>.complete() {
    suspendCoroutine<T> { continuation ->
        addOnSuccessListener { continuation.resume(it) }.addOnFailureListener { continuation.resumeWithException(it) }
    }
}