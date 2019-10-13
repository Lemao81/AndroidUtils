package com.jueggs.andutils.logging

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log_entries")
data class LogEntryEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "logLevel") var logLevel: String,
    @ColumnInfo(name = "message") var message: String?,
    @ColumnInfo(name = "method") var method: String,
    @ColumnInfo(name = "timestamp_string") var timestampString: String,
    @ColumnInfo(name = "timestamp_millis") var timestampMillis: Long,
    @ColumnInfo(name = "values") var values: String?,
    @ColumnInfo(name = "exception") var exception: String?,
    @ColumnInfo(name = "is_debug") var isDebug: Boolean,
    @ColumnInfo(name = "flavor") var flavor: String
)