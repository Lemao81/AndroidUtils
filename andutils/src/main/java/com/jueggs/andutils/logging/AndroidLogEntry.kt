package com.jueggs.andutils.logging

import com.jueggs.jutils.logging.ILogEntry
import com.jueggs.jutils.logging.ILogEntryBuilder
import com.jueggs.jutils.logging.ILogManager
import com.jueggs.jutils.logging.LogCategory
import com.jueggs.jutils.logging.LogLevel
import org.joda.time.DateTime

class AndroidLogEntry(
    override val message: String,
    override val category: String,
    override val logLevel: LogLevel,
    override val timestamp: DateTime,
    override val method: String,
    override val exception: Throwable?,
    override val valueMap: Map<String, Any?>?,
    override val isDebug: Boolean,
    override val flavor: String
) : ILogEntry {

    class Builder(
        override val logManager: ILogManager,
        private val message: String,
        private val isDebug: Boolean,
        private val flavor: String
    ) : ILogEntryBuilder {
        private var category: String = "MYDEBUG"
        private var exception: Throwable? = null
        private var valueMap: MutableMap<String, Any?>? = null

        override fun addValue(name: String, obj: Any?): ILogEntryBuilder {
            if (valueMap == null) {
                valueMap = mutableMapOf()
            }
            valueMap?.put(name, obj)

            return this
        }

        override fun withCategory(category: String): ILogEntryBuilder {
            this.category = category

            return this
        }

        override fun withCategory(category: LogCategory): ILogEntryBuilder {
            this.category = category.toString()

            return this
        }

        override fun withException(exception: Throwable): ILogEntryBuilder {
            this.exception = exception

            return this
        }

        override fun logDebug() = executeLogAction(LogLevel.DEBUG)

        override suspend fun logDebugAsync() = executeLogAsyncAction(LogLevel.DEBUG)

        override fun logInfo() = executeLogAction(LogLevel.INFO)

        override suspend fun logInfoAsync() = executeLogAsyncAction(LogLevel.INFO)

        override fun logWarning() = executeLogAction(LogLevel.WARNING)

        override suspend fun logWarningAsync() = executeLogAsyncAction(LogLevel.WARNING)

        override fun logError() = executeLogAction(LogLevel.ERROR)

        override suspend fun logErrorAsync() = executeLogAsyncAction(LogLevel.ERROR)

        override fun logFatal() = executeLogAction(LogLevel.FATAL)

        override suspend fun logFatalAsync() = executeLogAsyncAction(LogLevel.FATAL)

        private fun executeLogAction(logLevel: LogLevel) = this.logManager.log(build(logLevel))

        private suspend fun executeLogAsyncAction(logLevel: LogLevel) = this.logManager.logAsync(build(logLevel))

        private fun build(logLevel: LogLevel): AndroidLogEntry {
            val stackElement = Thread.currentThread().stackTrace[5]
            val method = "${stackElement.className}.${stackElement.methodName}()"

            return AndroidLogEntry(
                message = message,
                category = category,
                logLevel = logLevel,
                timestamp = DateTime.now(),
                method = method,
                exception = exception,
                valueMap = valueMap,
                isDebug = isDebug,
                flavor = flavor
            )
        }
    }
}