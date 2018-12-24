package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

object DateTimeLongConverter {
    @TypeConverter
    @JvmStatic
    fun dateTimeToLong(dateTime: DateTime?): Long? = dateTime?.millis

    @TypeConverter
    @JvmStatic
    fun longToDateTime(long: Long?): DateTime? = long?.let { DateTime(it) }
}