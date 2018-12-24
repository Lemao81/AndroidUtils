package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.LocalDate

object LocalDateStringConverter {
    @TypeConverter
    @JvmStatic
    fun dateToString(localDate: LocalDate?): String? = localDate?.toString()

    @TypeConverter
    @JvmStatic
    fun stringToDate(dateString: String?): LocalDate? = dateString?.let { LocalDate.parse(dateString) }
}