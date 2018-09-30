package com.jueggs.andutils.extension

import com.google.android.gms.tasks.Task
import com.jueggs.andutils.util.logException
import io.reactivex.*

fun Task<Void>.then(action: () -> Unit) = addOnCompleteListener { action() }

fun Task<Void>.success(action: () -> Unit) = addOnSuccessListener { action() }

fun Task<Void>.complete(action: () -> Unit) = addOnCompleteListener { action() }

fun Task<Void>.logFailure() = addOnFailureListener { logException(it) }

fun <T> Task<T>.then(action: (Task<T>) -> Unit) = addOnCompleteListener { action(it) }

fun <T> Task<T>.success(action: (T) -> Unit) = addOnSuccessListener { action(it) }

fun <T> Task<T>.fail(action: (Exception) -> Unit) = addOnFailureListener { action(it) }

fun <T> Task<T>.complete(action: (Task<T>) -> Unit) = addOnCompleteListener { action(it) }

fun <T> Task<T>.asSingle(): Single<T> = Single.create { emitter ->
    success(emitter::onSuccess)
    fail(emitter::onError)
}