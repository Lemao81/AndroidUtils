package com.jueggs.andutils.logging

import com.jueggs.jutils.logging.AbstractLogTarget
import com.jueggs.jutils.logging.ILogEntry
import com.jueggs.jutils.logging.ILogEntryBuilder
import com.jueggs.jutils.logging.ILogManager

class AndroidLogManager(
    override val targets: List<AbstractLogTarget>,
    private val isDebug: Boolean,
    private val flavor: String
) : ILogManager {

    override fun newEntry(message: String): ILogEntryBuilder = AndroidLogEntry.Builder(this, message, isDebug, flavor)

    override fun log(entry: ILogEntry) = targets.forEach { it.log(entry) }

    override suspend fun logAsync(entry: ILogEntry) = targets.forEach { it.logAsync(entry) }
}