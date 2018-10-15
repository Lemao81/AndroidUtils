package com.jueggs.firebaseutils.extension

import com.google.firebase.database.*
import com.jueggs.firebaseutils.DESERIALIZATION_FAILED

fun <T> DataSnapshot.deserialize(): T = getValue(GenericTypeIndicator()) ?: throw Exception(DESERIALIZATION_FAILED)