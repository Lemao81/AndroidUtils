package com.jueggs.jutils.helper

class DistinctRandomString(length: Int, symbols: String = RandomString.alphanum) {
    private val randomString = RandomString(length, symbols = symbols)
    private val set = mutableSetOf<String>()

    fun nextString(): String {
        var result: String
        do {
            result = randomString.nextString()
        } while (set.contains(result))
        set.add(result)
        return result
    }

    fun reset() = set.clear()
}