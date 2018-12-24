package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

object DateTimeUnixLongConverter {
    @TypeConverter
    @JvmStatic
    fun dateTimeToUnixLong(dateTime: DateTime?): Long? = dateTime?.let { it.millis / 1000 }

    @TypeConverter
    @JvmStatic
    fun unixLongToDateTime(long: Long?): DateTime? = long?.let { DateTime(it * 1000) }
}