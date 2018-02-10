package com.jueggs.utils

import java.text.SimpleDateFormat
import java.util.Date

class DateFormatter {
    private val simpleDateFormatCache = hashMapOf<String, SimpleDateFormat>()

    fun format(millis: Long, format: String): String {
        if (!simpleDateFormatCache.containsKey(format)) simpleDateFormatCache.put(format, SimpleDateFormat(format))
        val simpleDateFormat = simpleDateFormatCache[format]
        if (simpleDateFormat != null) return simpleDateFormat.format(Date(millis))
        return EMPTY_STRING
    }
}