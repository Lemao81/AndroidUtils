package com.jueggs.andutils.base

import android.app.*
import com.jueggs.andutils.util.logUnhandledException

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initialize()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
    }

    abstract fun initialize()
}