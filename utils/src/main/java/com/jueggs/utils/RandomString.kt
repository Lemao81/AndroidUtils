package com.jueggs.utils

import java.util.*

class RandomString @JvmOverloads constructor(length: Int = 21, random: Random = Random(), symbols: String = alphanum) {
    private val random: Random
    private val symbols: CharArray
    private val buffer: CharArray

    fun nextString(): String {
        for (index in buffer.indices)
            buffer[index] = symbols[random.nextInt(symbols.size)]
        return String(buffer)
    }

    init {
        if (length < 1) throw IllegalArgumentException()
        if (symbols.length < 2) throw IllegalArgumentException()
        this.random = Objects.requireNonNull(random)
        this.symbols = symbols.toCharArray()
        this.buffer = CharArray(length)
    }

    companion object {
        val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lower = upper.toLowerCase(Locale.ROOT)
        val digits = "0123456789"
        val alphanum = upper + lower + digits
    }
}