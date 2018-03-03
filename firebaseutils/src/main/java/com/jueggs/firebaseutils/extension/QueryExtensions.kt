package com.jueggs.firebaseutils.extension

import com.google.firebase.database.*
import io.reactivex.*

fun Query.readData(): Single<DataSnapshot> = Single.create { emitter ->
    addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(data: DataSnapshot?) {
            if (data != null) emitter.onSuccess(data)
            else emitter.onError(DatabaseException("${Query::class.simpleName}.readData: datasnapshot is null"))
        }

        override fun onCancelled(error: DatabaseError?) {
            if (error != null) emitter.onError(error.toException())
            else emitter.onError(DatabaseException("${Query::class.simpleName}.readData: ${ValueEventListener::onCancelled.name} was called"))
        }
    })
}

fun <T> Query.readModel(): Single<T> = readData().map { dataSnapshot ->
    dataSnapshot.getValue(GenericTypeIndicator()) ?: throw DatabaseException("${Query::class.simpleName}.readModel: deserialization returned null")
}

fun <T : Any> Query.readList(): Single<List<T>> = readData().map { dataSnapshot ->
    val list = arrayListOf<T>()
    dataSnapshot.children?.mapNotNullTo(list) { it.getValue<T>(GenericTypeIndicator()) }
    list
}