package com.jueggs.andutils

import android.graphics.Color
import com.jueggs.jutils.Random

object Random {
    fun color(red: Boolean = true, green: Boolean = true, blue: Boolean = true): Int {
        val redTone = if (red) Random.int(0, 255) else 0
        val greenTone = if (green) Random.int(0, 255) else 0
        val blueTone = if (blue) Random.int(0, 255) else 0

        return Color.rgb(redTone, greenTone, blueTone)
    }
}