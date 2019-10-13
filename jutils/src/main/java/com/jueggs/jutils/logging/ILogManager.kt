package com.jueggs.jutils.logging

interface ILogManager {
    val targets: List<ILogTarget>

    fun newEntry(message: String = ""): ILogEntryBuilder

    fun log(entry: ILogEntry)

    suspend fun logAsync(entry: ILogEntry)
}