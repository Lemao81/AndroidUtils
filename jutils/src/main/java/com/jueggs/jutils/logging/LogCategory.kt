package com.jueggs.jutils.logging

enum class LogCategory {
    NOTIFICATION,
    DATABASE,
    ANIMATION,
    UNHANDLEDEXCEPTION;

    override fun toString() = "MY${super.toString()}"
}