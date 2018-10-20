package com.jueggs.andutils.extension

import com.google.android.gms.tasks.Task
import com.jueggs.andutils.util.logException
import io.reactivex.*
import kotlin.coroutines.experimental.suspendCoroutine

fun Task<Void>.then(action: () -> Unit) = addOnCompleteListener { action() }

fun Task<Void>.success(action: () -> Unit) = addOnSuccessListener { action() }

fun Task<Void>.fail(action: () -> Unit) = addOnFailureListener { action() }

fun Task<Void>.logFailure() = addOnFailureListener { logException(it) }

fun <T> Task<T>.then(action: (Task<T>) -> Unit) = addOnCompleteListener { action(it) }

fun <T> Task<T>.success(action: (T) -> Unit) = addOnSuccessListener { action(it) }

fun <T> Task<T>.fail(action: (Exception) -> Unit) = addOnFailureListener { action(it) }

fun <T> Task<T>.asSingle(): Single<T> = Single.create { emitter ->
    success(emitter::onSuccess)
    fail(emitter::onError)
}

suspend fun <T> Task<T>.await(): T = suspendCoroutine { continuation ->
    addOnSuccessListener { continuation.resume(it) }.addOnFailureListener { continuation.resumeWithException(it) }
}