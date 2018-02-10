package com.jueggs.utils.base.mvp

import android.app.Application
import com.jueggs.utils.logUnhandledException

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
        initialize()
    }

    abstract fun initialize()
}