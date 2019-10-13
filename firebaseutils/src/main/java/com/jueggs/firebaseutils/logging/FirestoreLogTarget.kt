package com.jueggs.firebaseutils.logging

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.jueggs.jutils.logging.ILogEntry
import com.jueggs.jutils.logging.ILogTarget
import com.jueggs.jutils.service.JsonSerializer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.util.*

class FirestoreLogTarget(context: Context) : ILogTarget {
    private val packageName = context.packageName
    @ImplicitReflectionSerializer
    private val serializer = JsonSerializer(Json(JsonConfiguration.Default))

    init {
        FirebaseApp.initializeApp(context)
    }

    @ImplicitReflectionSerializer
    override fun log(entry: ILogEntry) {
        val entries = hashMapOf(
            "packagename" to packageName,
            "category" to entry.category,
            "exception" to entry.exception?.toString(),
            "logLevel" to entry.logLevel.toString(),
            "message" to entry.message,
            "method" to entry.method,
            "timestamp" to Date(entry.timestamp.millis),
            "values" to entry.valueMap?.let { stringifyValues(it) },
            "isDebug" to entry.isDebug,
            "flavor" to entry.flavor
        )

        val appNamePrefix = packageName.substring(packageName.indexOf('.', packageName.indexOf('.') + 1) + 1).take(6)
        val documentId = "${appNamePrefix}_${entry.timestamp.toString("yyyyMMdd-HHmmss.SSS")}"
        FirebaseFirestore.getInstance().collection("logEntries").document(documentId).set(entries)
    }

    @ImplicitReflectionSerializer
    override suspend fun logAsync(entry: ILogEntry) {
        GlobalScope.launch { log(entry) }
    }

    @ImplicitReflectionSerializer
    private fun stringifyValues(valueMap: Map<String, Any?>): String = serializer.toJson(valueMap, String::class).toString()
}