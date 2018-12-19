package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import com.jueggs.jutils.extension.unixDate
import com.jueggs.jutils.extension.unixTime
import java.util.Date

class DateUnixTimeConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.unixTime

    @TypeConverter
    fun longToDate(long: Long?): Date? = long?.unixDate
}