package com.jueggs.andutils.base

import android.app.*
import com.jueggs.andutils.util.logUnhandledException
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val koinModules = koinModules()
        if (koinModules != null)
            startKoin(koinModules, koinProperties())

        initialize()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable -> logUnhandledException(throwable) }
    }

    open fun koinModules(): List<Module>? = null
    open fun koinProperties(): Map<String, Any> = HashMap()

    open fun initialize() {}
}