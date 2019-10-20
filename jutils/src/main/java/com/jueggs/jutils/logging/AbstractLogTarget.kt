package com.jueggs.jutils.logging

abstract class AbstractLogTarget {
    abstract val levelThreshold: LogLevel

    fun log(entry: ILogEntry) {
        if (entry.logLevel >= levelThreshold) {
            doLog(entry)
        }
    }

    suspend fun logAsync(entry: ILogEntry) {
        if (entry.logLevel >= levelThreshold) {
            doLogAsync(entry)
        }
    }

    protected abstract fun doLog(entry: ILogEntry)
    protected abstract suspend fun doLogAsync(entry: ILogEntry)
}