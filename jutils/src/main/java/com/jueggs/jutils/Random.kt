package com.jueggs.jutils

import org.joda.time.Days
import org.joda.time.LocalDate
import java.lang.IllegalStateException
import java.util.Date
import java.util.GregorianCalendar
import java.util.Random
import java.util.concurrent.TimeUnit

object Random {
    private val random = lazy { Random(System.currentTimeMillis()) }

    /**
     * Random int between min and max, including them
     */
    fun int(min: Int = 0, max: Int = Int.MAX_VALUE - 1): Int = random.value.nextInt(max - min + 1) + min

    val long: Long = (int() * int()).toLong()

    val boolean: Boolean = random.value.nextBoolean()

    fun float(min: Float = 0f, max: Float = 1f) = (max - min) * random.value.nextFloat() + min

    fun double(min: Double = 0.0, max: Double = 1.0) = (max - min) * random.value.nextDouble() + min

    fun double2Decimal(min: Double = 0.0, max: Double = 1.0) = Math.floor(double(min, max) * 100) / 100

    fun float2Decimal(min: Float = 0f, max: Float = 1f) = (Math.floor((float(min, max) * 100).toDouble()) / 100).toFloat()

    fun date(from: Date = GregorianCalendar(1970, 0, 1).time, to: Date = Date()): Date {
        if (to.before(from)) throw IllegalStateException("To-date is before from-date")
        val daysBetween = TimeUnit.DAYS.convert(to.time - from.time, TimeUnit.MILLISECONDS).toInt()
        val millisToAdd = TimeUnit.MILLISECONDS.convert(int(0, daysBetween).toLong(), TimeUnit.DAYS)

        return Date(from.time + millisToAdd)
    }

    fun localDate(from: LocalDate = LocalDate(1970, 1, 1), to: LocalDate = LocalDate.now()): LocalDate {
        if (to.isBefore(from)) throw IllegalStateException("To-date is before from-date")
        val daysBetween = Days.daysBetween(from, to).days
        val toAdd = int(0, daysBetween)

        return from.plusDays(toAdd)
    }

    fun <T> weightedSelect(left: T, right: T, weightTowardsLeft: Double): T {
        assert(weightTowardsLeft < 1.0)

        return if (random.value.nextDouble() < weightTowardsLeft) left else right
    }
}