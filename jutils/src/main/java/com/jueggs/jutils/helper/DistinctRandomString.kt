package com.jueggs.jutils.helper

class DistinctRandomString(length: Int, symbols: String = RandomString.alphanum) {
    private val randomString = RandomString(length, symbols = symbols)
    private val set = mutableSetOf<String>()

    operator fun invoke(): String {
        var result: String
        do {
            result = randomString()
        } while (set.contains(result))
        set.add(result)

        return result
    }

    fun reset() = set.clear()
}