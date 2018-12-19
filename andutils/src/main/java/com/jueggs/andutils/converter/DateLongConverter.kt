package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import java.util.Date

class DateLongConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.time

    @TypeConverter
    fun longToDate(long: Long?): Date? = if (long == null) null else Date(long)
}