package com.jueggs.andutils.base

import android.app.Application
import com.jueggs.andutils.Constant.LOG4K_PATTERN_ALL
import com.jueggs.andutils.Constant.TAG_DEBUG
import com.jueggs.andutils.e
import com.log4k.Level
import com.log4k.Log4k.add
import com.log4k.android.AndroidAppender

abstract class BaseApplication(private val debug: Boolean) : Application() {
    override fun onCreate() {
        super.onCreate()
        if (debug) add(Level.Verbose, LOG4K_PATTERN_ALL, AndroidAppender(generateClassName = { "$TAG_DEBUG - ${it.substring(it.lastIndexOf('.') + 1)}" }))
        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> e(throwable) }

        initialize()
    }

    open fun initialize() {}
}