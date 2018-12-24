package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

object UnixLongStringDateTimeConverter {
    @TypeConverter
    @JvmStatic
    fun unixLongToString(long: Long?): String? = long?.let { DateTime(it * 1000).toString() }

    @TypeConverter
    @JvmStatic
    fun stringToUnixLong(string: String?): Long? = string?.let { DateTime.parse(it).millis / 1000 }
}