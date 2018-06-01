package com.jueggs.andutils.helper

import android.arch.persistence.room.TypeConverter
import com.jueggs.jutils.extension.*
import java.util.*

class UnixDateConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.unixTime

    @TypeConverter
    fun longToDate(long: Long?): Date? = long?.unixDate
}