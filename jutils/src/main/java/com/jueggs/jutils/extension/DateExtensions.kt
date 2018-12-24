package com.jueggs.jutils.extension

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.Years
import java.util.Calendar
import java.util.Date

val Calendar.year: Int
    get() = get(Calendar.YEAR)

val Calendar.month: Int
    get() = get(Calendar.MONTH)

val Calendar.dayOfMonth: Int
    get() = get(Calendar.DAY_OF_MONTH)

val Date.unixTime: Long
    get() = time / 1000

val LocalDate.unixTime: Long
    get() = toDateTimeAtStartOfDay().millis / 1000

val DateTime.unixTime: Long
    get() = millis / 1000

val Long.unixDate: Date
    get() = Date(this * 1000)

val Long.unixLocalDate: LocalDate
    get() = LocalDate(this * 1000)

val Long.unixDateTime: DateTime
    get() = DateTime(this * 1000)

fun LocalDate.toAge() = Years.yearsBetween(this, LocalDate.now()).years