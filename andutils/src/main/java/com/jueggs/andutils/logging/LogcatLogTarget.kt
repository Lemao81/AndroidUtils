package com.jueggs.andutils.logging

import android.util.Log
import com.jueggs.jutils.extension.join
import com.jueggs.jutils.logging.ILogEntry
import com.jueggs.jutils.logging.ILogTarget
import com.jueggs.jutils.logging.LogLevel
import com.jueggs.jutils.service.JsonSerializer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class LogcatLogTarget : ILogTarget {
    @ImplicitReflectionSerializer
    private val serializer = JsonSerializer(Json(JsonConfiguration.Default))

    @ImplicitReflectionSerializer
    override fun log(entry: ILogEntry) {
        val messageParts = mutableListOf<String>()
        if (entry.message.isNotBlank()) {
            messageParts.add("Message: ${entry.message}")
        }
        if (entry.method.isNotBlank()) {
            messageParts.add("Method: ${entry.method}")
        }
        if (!entry.valueMap.isNullOrEmpty()) {
            entry.valueMap?.let { messageParts.add("Values: ${stringifyValues(it)}") }
        }
        val message = messageParts.join("  |  ")
        when (entry.logLevel) {
            LogLevel.DEBUG -> Log.d(entry.category, message, entry.exception)
            LogLevel.INFO -> Log.i(entry.category, message, entry.exception)
            LogLevel.WARNING -> Log.w(entry.category, message, entry.exception)
            LogLevel.ERROR -> Log.e(entry.category, message, entry.exception)
            LogLevel.FATAL -> Log.e(entry.category, message, entry.exception)
        }
    }

    @ImplicitReflectionSerializer
    override suspend fun logAsync(entry: ILogEntry) {
        GlobalScope.launch { log(entry) }
    }

    @ImplicitReflectionSerializer
    private fun stringifyValues(valueMap: Map<String, Any?>): String = serializer.toJson(valueMap, String::class).toString()
}