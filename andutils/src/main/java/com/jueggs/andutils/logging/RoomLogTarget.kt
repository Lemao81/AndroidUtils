package com.jueggs.andutils.logging

import com.jueggs.jutils.logging.AbstractLogTarget
import com.jueggs.jutils.logging.ILogEntry
import com.jueggs.jutils.logging.LogLevel
import com.jueggs.jutils.service.JsonSerializer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class RoomLogTarget(private val logDao: ILogDao, override val levelThreshold: LogLevel = LogLevel.ERROR) : AbstractLogTarget() {
    @ImplicitReflectionSerializer
    private val serializer = JsonSerializer(Json(JsonConfiguration.Default))

    @ImplicitReflectionSerializer
    override fun doLog(entry: ILogEntry) {
        val entryEntity = LogEntryEntity(
            category = entry.category,
            logLevel = entry.logLevel.toString(),
            method = entry.method,
            message = entry.message,
            timestampString = entry.timestamp.toString(),
            timestampMillis = entry.timestamp.millis,
            exception = entry.exception?.toString(),
            values = entry.valueMap?.let { stringifyValues(it) },
            isDebug = entry.isDebug,
            flavor = entry.flavor
        )

        logDao.insert(entryEntity)
    }

    @ImplicitReflectionSerializer
    override suspend fun doLogAsync(entry: ILogEntry) {
        GlobalScope.launch { log(entry) }
    }

    @ImplicitReflectionSerializer
    private fun stringifyValues(valueMap: Map<String, Any?>): String = serializer.toJson(valueMap, String::class).toString()
}