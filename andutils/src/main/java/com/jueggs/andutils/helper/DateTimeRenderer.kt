package com.jueggs.andutils.helper

import com.jueggs.andutils.DEFAULT_DATETIME_FORMAT
import org.joda.time.*

class DateTimeRenderer(private val format: String? = DEFAULT_DATETIME_FORMAT) {
    operator fun invoke(millis: Long) = invoke(DateTime(millis))

    operator fun invoke(then: DateTime): String {
        val now = DateTime()
        return when {
            then.isWithinLastMinute(now) -> "just now"
            then.isWithinLastHour(now) -> {
                val minutes = now.passedMinutesSince(then)
                "$minutes ${renderMinute(minutes)} ago"
            }
            then.isCurrentDay(now) -> {
                val hours = now.passedHoursSince(then)
                "$hours ${renderHour(hours)} ago"
            }
            then.isYesterday(now) -> "yesterday"
            then.isWithinOneWeek(now) -> {
                val days = now.passedDaysSince(then)
                "$days ${renderDays(days)} ago"
            }
            else -> then.toString(format)
        }
    }

    private fun renderMinute(count: Int) = if (count == 1) "minute" else "minutes"
    private fun renderHour(count: Int) = if (count == 1) "hour" else "hours"
    private fun renderDays(count: Int) = if (count == 1) "hour" else "hours"
}

private fun DateTime.passedHoursSince(now: DateTime) = Hours.hoursBetween(now.toInstant(), toInstant()).hours
private fun DateTime.passedMinutesSince(now: DateTime) = Minutes.minutesBetween(now.toInstant(), toInstant()).minutes
private fun DateTime.passedDaysSince(now: DateTime) = Days.daysBetween(now.toInstant(), toInstant()).days

private fun DateTime.isWithinLastMinute(now: DateTime) = now.minusMinutes(1).isBefore(toInstant())
private fun DateTime.isWithinLastHour(now: DateTime) = now.minusHours(1).isBefore(toInstant())
private fun DateTime.isWithinOneWeek(now: DateTime) = now.minusWeeks(1).isBefore(toInstant())

private fun DateTime.isCurrentDay(now: DateTime) = dayOfMonth == now.dayOfMonth && monthOfYear == now.monthOfYear && year == now.year
private fun DateTime.isYesterday(now: DateTime) = plusDays(1).dayOfMonth == now.dayOfMonth && monthOfYear == now.monthOfYear && year == now.year
