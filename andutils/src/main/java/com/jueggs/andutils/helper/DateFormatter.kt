package com.jueggs.andutils.helper

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
    private val simpleDateFormatCache = hashMapOf<String, SimpleDateFormat>()

    operator fun invoke(millis: Long, format: String): String {
        if (!simpleDateFormatCache.containsKey(format)) simpleDateFormatCache.put(format, SimpleDateFormat(format))
        val simpleDateFormat = simpleDateFormatCache[format]
        if (simpleDateFormat != null) return simpleDateFormat.format(Date(millis))

        return ""
    }
}