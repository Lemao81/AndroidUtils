package com.jueggs.andutils.converter

import androidx.room.TypeConverter
import org.joda.time.LocalDate

class LocalDateStringConverter {
    @TypeConverter
    fun dateToString(localDate: LocalDate?): String? = localDate?.toString()

    @TypeConverter
    fun stringToDate(dateString: String?): LocalDate? = dateString?.let { LocalDate.parse(dateString) }
}