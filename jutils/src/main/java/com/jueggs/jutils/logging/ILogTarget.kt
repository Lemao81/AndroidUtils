package com.jueggs.jutils.logging

import com.jueggs.jutils.logging.ILogEntry

interface ILogTarget {
    fun log(entry: ILogEntry)

    suspend fun logAsync(entry: ILogEntry)
}