package com.jueggs.andutils.util

class Action<T>(private val action: T.() -> T) {
    operator fun invoke(obj: T): T = obj.action()
}