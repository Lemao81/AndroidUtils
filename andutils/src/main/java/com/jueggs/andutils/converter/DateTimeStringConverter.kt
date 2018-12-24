package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

object DateTimeStringConverter {
    @TypeConverter
    @JvmStatic
    fun dateTimeToString(dateTime: DateTime?): String? = dateTime?.toString()

    @TypeConverter
    @JvmStatic
    fun stringToDateTime(string: String?): DateTime? = string?.let { DateTime.parse(it) }
}