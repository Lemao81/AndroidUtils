package com.jueggs.andutils.helper

import androidx.room.TypeConverter
import com.jueggs.jutils.extension.unixDate
import com.jueggs.jutils.extension.unixTime
import java.util.Date

class UnixDateConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.unixTime

    @TypeConverter
    fun longToDate(long: Long?): Date? = long?.unixDate
}