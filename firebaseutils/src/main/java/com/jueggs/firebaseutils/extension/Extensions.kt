package com.jueggs.firebaseutils.extension

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.GenericTypeIndicator
import com.jueggs.firebaseutils.DESERIALIZATION_FAILED

fun <T> DataSnapshot.deserializeList(): List<T> = getValue(object : GenericTypeIndicator<List<T>>() {}) ?: throw Exception(DESERIALIZATION_FAILED)