package com.jueggs.andutils.helper

import org.joda.time.*

class DateRenderer(private val format: String = "dd.MM.yyyy - hh:mm") {
    fun render(millis: Long) = render(DateTime(millis))

    fun render(then: DateTime): String {
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
