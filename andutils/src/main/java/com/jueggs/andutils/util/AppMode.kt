package com.jueggs.andutils.util

object AppMode {
    var singlePane: Boolean = true
        set(value) {
            if (value)
                twoPane = false
            field = value
        }

    var twoPane: Boolean = false
        set(value) {
            if (value)
                singlePane = false
            field = value
        }
}