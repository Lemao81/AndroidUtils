package com.jueggs.andutils.helper

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.time

    @TypeConverter
    fun longToDate(long: Long?): Date? = if (long == null) null else Date(long)
}