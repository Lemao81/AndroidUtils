package com.jueggs.utils

import org.joda.time.DateTime
import org.joda.time.DateTimeFieldType
import org.joda.time.Hours
import org.joda.time.Minutes

class DateRenderer {
    private fun render(date: DateTime): String {
        return when {
            date.isWithinLastMinute() -> "just now"
            date.isWithinLastHour() -> {
                val passedMinutes = date.passedMinutes()
                val minuteString = if (passedMinutes == 1) "minute" else "minutes"
                "$passedMinutes $minuteString ago"
            }
            date.isCurrentDay() -> {
                val passedHours = date.passedHours()
                val hourString = if (passedHours == 1) "hour" else "hours"
                "$passedHours $hourString ago"
            }
            else -> date.toString("dd.MM.yyyy - hh:mm")
        }
    }

    fun render(millis: Long): String = render(DateTime(millis))
}

private fun DateTime.passedHours(): Int = Hours.hoursBetween(this.toInstant(), DateTime().toInstant()).hours

private fun DateTime.isCurrentDay(): Boolean {
    val now = DateTime()
    val dayOfYearNow = now.get(DateTimeFieldType.dayOfYear())
    val yearNow = now.get(DateTimeFieldType.year())
    return dayOfYearNow == get(DateTimeFieldType.dayOfYear()) && yearNow == get(DateTimeFieldType.year())
}

private fun DateTime.isWithinLastMinute(): Boolean = DateTime().minusMinutes(1).isBefore(toInstant())

private fun DateTime.isWithinLastHour(): Boolean = DateTime().minusHours(1).isBefore(toInstant())

private fun DateTime.passedMinutes(): Int = Minutes.minutesBetween(this.toInstant(), DateTime().toInstant()).minutes
