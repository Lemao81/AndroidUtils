package com.jueggs.utils.base

import android.app.Application
import com.jueggs.andutils.util.logUnhandledException

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
        initialize()
    }

    abstract fun initialize()
}