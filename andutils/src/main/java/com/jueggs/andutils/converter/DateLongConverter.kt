package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import java.util.Date

object DateLongConverter {
    @TypeConverter
    @JvmStatic
    fun dateToLong(date: Date?): Long? = date?.time

    @TypeConverter
    @JvmStatic
    fun longToDate(long: Long?): Date? = if (long == null) null else Date(long)
}