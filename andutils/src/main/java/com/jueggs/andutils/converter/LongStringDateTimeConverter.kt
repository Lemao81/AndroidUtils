package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

object LongStringDateTimeConverter {
    @TypeConverter
    @JvmStatic
    fun longToString(long: Long?): String? = long?.let { DateTime(it).toString() }

    @TypeConverter
    @JvmStatic
    fun stringToLong(string: String?): Long? = string?.let { DateTime.parse(it).millis }
}