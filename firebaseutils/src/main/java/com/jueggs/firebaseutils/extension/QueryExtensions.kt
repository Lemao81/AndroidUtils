package com.jueggs.firebaseutils.extension

import com.google.firebase.database.*
import com.jueggs.firebaseutils.*
import io.reactivex.*
import kotlin.coroutines.experimental.suspendCoroutine

fun Query.toDataSingle(): Single<DataSnapshot> = Single.create { emitter ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(data: DataSnapshot?) {
            if (data != null) emitter.onSuccess(data)
            else emitter.onError(Exception(NULL_DATA))
        }

        override fun onCancelled(error: DatabaseError?) = emitter.onError(error?.toException() ?: Exception())
    })
}

fun <T> Query.toModelSingle(): Single<T> = toDataSingle().map { it.deserialize<T>() }

fun <T : Any> Query.toModelListSingle(): Single<List<T>> = toDataSingle().map { dataSnapshot ->
    val list = arrayListOf<T>()
    dataSnapshot.children?.mapNotNullTo(list) { it.getValue<T>(GenericTypeIndicator()) }
    list
}

suspend fun Query.await(): DataSnapshot = suspendCoroutine { continuation ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(data: DataSnapshot?) {
            if (data != null) continuation.resume(data)
            else continuation.resumeWithException(Exception(NULL_DATA))
        }

        override fun onCancelled(error: DatabaseError?) = continuation.resumeWithException(error?.toException() ?: Exception())
    })
}