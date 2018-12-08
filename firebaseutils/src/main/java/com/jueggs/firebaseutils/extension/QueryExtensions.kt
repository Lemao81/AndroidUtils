package com.jueggs.firebaseutils.extension

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun Query.toDataSingle(): Single<DataSnapshot> = Single.create { emitter ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(data: DataSnapshot) = emitter.onSuccess(data)
        override fun onCancelled(error: DatabaseError) = emitter.onError(error.toException())
    })
}

fun <T : Any> Query.toModelSingle(): Single<T> = toDataSingle().map { it.getValue(object : GenericTypeIndicator<T>() {}) }
fun <T : Any> Query.toModelListSingle(): Single<List<T>> = toDataSingle().map { it.getValue(object : GenericTypeIndicator<List<T>>() {}) }

suspend fun Query.await(): DataSnapshot = suspendCoroutine { continuation ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(data: DataSnapshot) = continuation.resume(data)
        override fun onCancelled(error: DatabaseError) = continuation.resumeWithException(error.toException())
    })
}