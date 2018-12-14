package com.jueggs.jutils.helper

import java.util.Random

class DistinctRandomString(length: Int = 21, symbols: String = RandomString.alphaNum, random: Random = Random(System.currentTimeMillis())) {
    private val randomString = RandomString(length, symbols, random)
    private val set = mutableSetOf<String>()

    operator fun invoke(): String {
        var result: String
        var count = 0

        do {
            result = randomString()
            count++
        } while (set.contains(result) || count >= 1000)

        if (count >= 1000) throw Exception("Took to long to find a distinct random string, increase length or symbols count")
        set.add(result)

        return result
    }

    fun reset() = set.clear()
}