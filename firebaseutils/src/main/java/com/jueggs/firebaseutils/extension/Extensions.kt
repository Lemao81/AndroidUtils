package com.jueggs.firebaseutils.extension

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.GenericTypeIndicator
import com.jueggs.firebaseutils.Constant.DESERIALIZATION_FAILED

/**
 * Can only be used if data had been persisted as list. Total of singly added units is considered as a hashmap and not deserializable in this way
 */
fun <T> DataSnapshot.deserializeList(): List<T> = getValue(object : GenericTypeIndicator<List<T>>() {}) ?: throw Exception(DESERIALIZATION_FAILED)