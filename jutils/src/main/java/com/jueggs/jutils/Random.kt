package com.jueggs.jutils

import com.jueggs.jutils.helper.DistinctRandomString
import com.jueggs.jutils.helper.RandomString
import org.joda.time.Days
import org.joda.time.LocalDate
import java.lang.IllegalStateException
import java.util.Date
import java.util.GregorianCalendar
import java.util.Random
import java.util.concurrent.TimeUnit

object Random {
    private val random = lazy { Random(System.currentTimeMillis()) }
    private val emailDomains = lazy { listOf("web.de", "yahoo.com", "gmx.net", "aol.com", "gmail.com", "gmx.de", "hotmail.com") }
    private val emailDistinctRandom = lazy { DistinctRandomString(6, RandomString.lowerAlphaNum, random.value) }

    /**
     * Random int between min and max, including them
     */
    fun int(min: Int = 0, max: Int = Int.MAX_VALUE - 1): Int = random.value.nextInt(max - min + 1) + min

    val long = (int() * int()).toLong()

    val boolean = random.value.nextBoolean()

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

    val email = "${emailDistinctRandom.value()}@${emailDomains.value.random()}"

    fun <T> weightedSelect(left: T, right: T, weightTowardsLeft: Double): T {
        assert(weightTowardsLeft < 1.0)

        return if (random.value.nextDouble() < weightTowardsLeft) left else right
    }
}